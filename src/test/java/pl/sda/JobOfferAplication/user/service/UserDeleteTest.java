package pl.sda.JobOfferAplication.user.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sda.JobOfferAplication.user.entity.UserEntity;
import pl.sda.JobOfferAplication.user.exception.UserException;
import pl.sda.JobOfferAplication.user.model.UserInput;
import pl.sda.JobOfferAplication.user.model.UserOutput;
import pl.sda.JobOfferAplication.user.repository.UserRepository;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.sda.JobOfferAplication.user.service.UserServiceImpl.NO_USER_FOUND_OR_GIVEN_ID;

@SpringBootTest
public class UserDeleteTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void deleteUserCorrectly() throws UserException {

        //given
        UserInput userInput = new UserInput("Michael", "mike", "blabla");
        userService.createUser(userInput);
        Long id = userService.getAllUsers().get(0).getUuid();


        //when
        userService.deleteUser(id);

        //then
        final List<UserEntity> all = userRepository.findAll();
        assertEquals(all.size(), 0);

    }
    @Test
    public void isDeletingMethodWithExceptionIsCorrect() throws UserException {

        //given
        UserInput userInput = new UserInput("Michael", "mike", "blabla");
        userService.createUser(userInput);
        Long id = userService.getAllUsers().get(0).getUuid();

        //when
        Executable executable = () ->  userService.deleteUser(id+1);

        //then
        UserException userException =assertThrows(UserException.class, executable);
        assertEquals(NO_USER_FOUND_OR_GIVEN_ID, userException.getMessage());
    }
}
