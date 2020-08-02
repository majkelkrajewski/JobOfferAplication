package pl.sda.JobOfferAplication.user.service;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.JobOfferAplication.user.entity.UserEntity;
import pl.sda.JobOfferAplication.user.exception.UserException;
import pl.sda.JobOfferAplication.user.model.UserInput;
import pl.sda.JobOfferAplication.user.model.UserOutput;
import pl.sda.JobOfferAplication.user.repository.UserRepository;

//import javax.persistence.Id;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{


    public static final String NO_USER_FOUND_OR_GIVEN_ID = "No user found or given id";
    public static final String THERE_IS_ALREADY_USER_WITH_THIS_NAME = "There is already user with this name";
    final private UserRepository userRepository;
    final private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createUser(UserInput userInput) throws UserException{

        validateUserInput(userInput);// walidacja czy login ma przynajmniej 6 znakow i haslo
                                    // 8 znakow i w tym 1 znak specjalny 1 wielka litere,
                                    // jedna mala i 1 przynajmniej  cyfre.

        final String encode = passwordEncoder.encode(userInput.getPassword());

        final UserEntity userEntity =
                new UserEntity(userInput.getName(),
                userInput.getLogin(),
                userInput.getCreationDate(),
                encode);

        userRepository.save(userEntity);
    }

    @Override
    public List<UserOutput> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserEntity::toOutput)
                .collect(Collectors.toList());

    }

    @Override
    public UserOutput getUserById(Long id) throws UserException {
        final Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
        if(!optionalUserEntity.isPresent()) {
            throw new UserException(NO_USER_FOUND_OR_GIVEN_ID);
        }
        return optionalUserEntity.get().toOutput();
    }

    private void validateUserInput(UserInput userInput) throws UserException{
        if(userRepository.existsUserEntitiesByLogin(userInput.getLogin())) {
            throw new UserException(THERE_IS_ALREADY_USER_WITH_THIS_NAME);
        }

    }

}
