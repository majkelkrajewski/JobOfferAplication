package pl.sda.JobOfferAplication.user.model;

import lombok.*;

import java.util.UUID;


@Getter
@ToString
public class UserInput {

    private String uuid;
    private String name;
    private String login;
    private String creationDate;
    private String password;

    private UserInput(){
        uuid = UUID.randomUUID().toString();
        creationDate = "now";

    }
}
