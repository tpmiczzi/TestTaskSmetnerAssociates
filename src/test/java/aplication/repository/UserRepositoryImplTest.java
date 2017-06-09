package aplication.repository;

import aplication.BaseTest;
import aplication.model.exception.UserAlreadyExistsException;
import aplication.model.user.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class UserRepositoryImplTest extends BaseTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void resetUsers() {
        userRepository.clearUsers();
    }

    @Test
    public void createNewUser() throws Exception {
        User user = getUser();
        userRepository.createUser(user);
    }

    @Test
    public void createUserWhenUserIsExists() throws Exception {
        User user = getUser();
        userRepository.createUser(user);
        try{
            userRepository.createUser(user);
            fail();
        } catch (UserAlreadyExistsException userAlreadyExistsException) {
            assertEquals(UserAlreadyExistsException.class, userAlreadyExistsException.getClass());
        }
    }

    private User getUser() {
        User user = new User();
        user.setId("TEST id");
        user.setFirstName("TEST first name");
        user.setLastName("TEST last name");
        user.setUserName("TEST user name");
        user.setPlainTextPassword("TEST plain text password");
        user.setHashedPassword("TEST hashed password");
        return user;
    }

}