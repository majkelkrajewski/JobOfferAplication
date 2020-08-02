package pl.sda.JobOfferAplication.user.service;

import pl.sda.JobOfferAplication.user.exception.UserException;
import pl.sda.JobOfferAplication.user.model.UserInput;
import pl.sda.JobOfferAplication.user.model.UserOutput;

import java.util.List;

public interface UserService {
    void createUser(UserInput userInput) throws UserException;
    List<UserOutput> getAllUsers();
    UserOutput getUserById(Long id) throws UserException;
}
