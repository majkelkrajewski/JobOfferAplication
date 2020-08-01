package pl.sda.JobOfferAplication.user.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserOutput {

    private String uuid;
    private String name;
    private String login;
    private String creationDate;
}
