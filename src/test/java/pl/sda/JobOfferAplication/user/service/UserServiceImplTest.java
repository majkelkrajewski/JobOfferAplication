package pl.sda.JobOfferAplication.user.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.sda.JobOfferAplication.user.entity.UserEntity;
import pl.sda.JobOfferAplication.user.exception.UserException;
import pl.sda.JobOfferAplication.user.model.UserInput;
import pl.sda.JobOfferAplication.user.model.UserOutput;
import pl.sda.JobOfferAplication.user.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void createUserCorrectly() throws UserException {

        //given
        UserInput userInput = new UserInput("Michael", "mike", "blabla");

        //when
        userService.createUser(userInput);

        //then
        final List<UserEntity> all = userRepository.findAll();
        assertEquals(all.size(), 1);
        final UserOutput userOutput = all.get(0).toOutput();

        assertEquals(userOutput.getLogin(), userInput.getLogin());

    }

    @Test
    public void getUserByIdIsCorrect ( ) throws UserException{
        //given
        UserEntity userEntity = new UserEntity ( "Piotr" , "GAGA" , LocalDate.now ( ) , "2gggFFFrerer2" );
        userRepository.save ( userEntity );
        Long id = userRepository.findAll().get(0).getId();

        //when
        UserOutput userById = userService.getUserById (id);
        //then
        assertTrue (!(userById == null));
        UserOutput userOutput = userEntity.toOutput ();
        assertEquals ( userOutput.getName () , userById.getName () );
        assertEquals ( userOutput.getLogin () , userById.getLogin () );
    }

    @Test
    public void test() throws Exception {

    }
}
