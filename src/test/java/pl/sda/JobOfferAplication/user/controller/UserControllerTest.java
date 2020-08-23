package pl.sda.JobOfferAplication.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.sda.JobOfferAplication.user.entity.UserEntity;
import pl.sda.JobOfferAplication.user.exception.UserException;
import pl.sda.JobOfferAplication.user.model.UserInput;
import pl.sda.JobOfferAplication.user.model.UserOutput;
import pl.sda.JobOfferAplication.user.repository.UserRepository;
import pl.sda.JobOfferAplication.user.service.UserService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.sda.JobOfferAplication.user.controller.UserController.USERS_MAPPING;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void deleteUser() throws Exception {
        //given
        UserInput userInput = new UserInput("Poldek", "xxxx", "Polaooo22JD");
        userService.createUser(userInput);
        Long id = userService.getAllUsers().get(0).getUuid();

        final MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(USERS_MAPPING+"/"+id)
                .contentType(MediaType.APPLICATION_JSON);

        // When
        final ResultActions resultActions = mockMvc.perform(requestBuilder);

       //then
        resultActions.andExpect(status().isNoContent());


    }

    public static String toJson(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
