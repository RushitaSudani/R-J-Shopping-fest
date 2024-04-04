package org.technous.validation.service;

import org.technous.validation.exception.UserException;
import org.technous.validation.model.User;

public interface UserService {
    public User saveUser(User user);
    public User findUserById(Long userId) throws UserException;

}
