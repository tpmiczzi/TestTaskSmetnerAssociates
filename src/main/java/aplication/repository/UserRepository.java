package aplication.repository;

import aplication.model.exception.UserAlreadyExistsException;
import aplication.model.user.User;

public interface UserRepository {
    User createUser(User user) throws UserAlreadyExistsException;

    void clearUsers();
}
