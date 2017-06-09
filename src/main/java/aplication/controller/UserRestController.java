package aplication.controller;

import aplication.model.exception.UserAlreadyExistsException;
import aplication.model.user.PayloadData;
import aplication.model.user.User;
import aplication.model.user.UserException;
import aplication.model.user.UserResponse;
import aplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class UserRestController {

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/userservice/register", produces = "application/json")
    public ResponseEntity<PayloadData> getRequest(@RequestParam(value = "firstName", defaultValue = "Some first name") String firstName,
                                                  @RequestParam(value = "lastName", defaultValue = "The last name") String lastName,
                                                  @RequestParam(value = "userName", defaultValue = "The user name") String userName,
                                                  @RequestParam(value = "plainTextPassword", defaultValue = "The password in plain text") String plainTextPassword) {
        String id = String.valueOf(counter.incrementAndGet());
        String hashedPassword = String.valueOf(plainTextPassword.hashCode());

        User tmp = new User(id, firstName, lastName, userName, plainTextPassword, hashedPassword);
        try {
            userRepository.createUser(tmp);
            return new ResponseEntity<>(new UserResponse(tmp), HttpStatus.OK);
        }catch (UserAlreadyExistsException userAlreadyExistsException){
            UserException tmpError = new UserException("USER_ALREADY_EXISTS", "A user with the given username already exists");
            return new ResponseEntity<>(tmpError, HttpStatus.CONFLICT);
        }
    }
}
