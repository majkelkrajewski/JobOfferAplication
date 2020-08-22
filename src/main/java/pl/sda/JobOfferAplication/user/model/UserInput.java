package pl.sda.JobOfferAplication.user.model;

import lombok.*;

import java.time.LocalDate;
//import java.util.UUID;


@Getter
@ToString
public class UserInput {

    private String name;
    private String login;
    private LocalDate creationDate;
    private String password;

    public UserInput(){
        creationDate = LocalDate.now();

    }

    public UserInput(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }
}
