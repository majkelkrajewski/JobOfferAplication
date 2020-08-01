package pl.sda.JobOfferAplication.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.JobOfferAplication.user.model.UserInput;
import pl.sda.JobOfferAplication.user.model.UserOutput;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    @GetMapping
    public ResponseEntity<List<UserOutput>> getAllUsers(){

        UserOutput userOutput = new UserOutput("1", "Adam", "adam123", "23123123");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Collections.singletonList(userOutput));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<UserOutput>> getUserById(@PathVariable(value = "id") Long id){

        System.out.println(id);
        final UserOutput userOutput = new UserOutput("1", "Adam", "adam123", "23123123");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Collections.singletonList(userOutput));
    }

    @PostMapping
    public ResponseEntity<Void> postUser(@RequestBody UserInput userInput){

        System.out.println(userInput);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }


}
