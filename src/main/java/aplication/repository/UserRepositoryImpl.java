package aplication.repository;

import aplication.model.exception.UserAlreadyExistsException;
import aplication.model.user.User;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {

    private Set<User> users = new HashSet<>();

    @Override
    public void clearUsers() {
        users.clear();
    }

    @Override
    public User createUser(User user) throws UserAlreadyExistsException {
        if (users.add(user)){
            return user;
        } else {
            throw new UserAlreadyExistsException(user);
        }
    }
}
