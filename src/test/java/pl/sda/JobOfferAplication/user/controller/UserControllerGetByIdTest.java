package pl.sda.JobOfferAplication.user.controller;

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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.sda.JobOfferAplication.JobOfferAplicationApplication;
import pl.sda.JobOfferAplication.user.model.UserInput;
import pl.sda.JobOfferAplication.user.repository.UserRepository;
import pl.sda.JobOfferAplication.user.service.UserService;

import static pl.sda.JobOfferAplication.user.controller.UserController.USERS_MAPPING;

@AutoConfigureMockMvc
@SpringBootTest(classes = JobOfferAplicationApplication.class)
class UserControllerGetByIdTest {

    private static final String ID = "1";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void getUserByIdProperly() throws Exception {
        //given
        UserInput userInput = new UserInput("lsdkfjslkd", "ksjdfhsk", "ksdjfh");
        userService.createUser(userInput);
        Long id = userService.getAllUsers().get(0).getUuid();


        final MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(USERS_MAPPING + "/" + id)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
        //when
        final ResultActions resultActions = mockMvc.perform(requestBuilder);
        //then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }
}