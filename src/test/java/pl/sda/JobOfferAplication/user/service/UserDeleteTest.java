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


        //when
        userService.deleteUser(1L);

        //then
        final List<UserEntity> all = userRepository.findAll();
        assertEquals(all.size(), 0);

    }
    @Test
    public void isDeletingMethodWithExeptionIsCorrect() throws UserException {

        //given
        UserInput userInput = new UserInput("Michael", "mike", "blabla");
        userService.createUser(userInput);


        //when
        Executable executable = () ->  userService.deleteUser(15L);

        //then

        UserException userException =assertThrows(UserException.class, executable);
        assertEquals(NO_USER_FOUND_OR_GIVEN_ID, userException.getMessage());
    }
}
