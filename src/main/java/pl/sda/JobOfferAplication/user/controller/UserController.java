package pl.sda.JobOfferAplication.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.JobOfferAplication.user.exception.UserException;
import pl.sda.JobOfferAplication.user.model.UserInput;
import pl.sda.JobOfferAplication.user.model.UserOutput;
import pl.sda.JobOfferAplication.user.service.UserService;

//import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(UserController.USERS_MAPPING)
public class UserController {


    public static final String USERS_MAPPING = "/users";
    final private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserOutput>> getAllUsers() {

        final List<UserOutput> allUsers = userService.getAllUsers();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allUsers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserOutput> getUserById(@PathVariable(value = "id") Long id) throws UserException {


        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<Void> postUser(@RequestBody UserInput userInput) throws UserException {

        userService.createUser(userInput);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }


}
