package pl.sda.JobOfferAplication.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.sda.JobOfferAplication.JobOfferAplicationApplication;
import pl.sda.JobOfferAplication.user.model.UserInput;
import pl.sda.JobOfferAplication.user.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.sda.JobOfferAplication.user.controller.UserController.USERS_MAPPING;

@AutoConfigureMockMvc
@SpringBootTest(classes = JobOfferAplicationApplication.class)
class UserControllerCreateTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void shouldCreateUserProperly() throws Exception {

        UserInput userInput = new UserInput("michal", "ksdjf", "efe");

        final MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(USERS_MAPPING)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(userInput));
        // When
        final ResultActions resultActions = mockMvc.perform(requestBuilder);
        //then
        resultActions.andExpect(status().isCreated());

    }
    public static String toJson(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}