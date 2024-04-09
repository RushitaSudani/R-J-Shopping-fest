package org.technous.validation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.technous.validation.exception.UserException;
import org.technous.validation.model.User;
import org.technous.validation.repository.UserRepository;
import org.technous.validation.service.UserService;

import java.util.Optional;

@Service
public class UserServiceIMPL implements UserService {

    @Autowired
    public UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    @Override
    public User findUserById(Long userId) throws UserException {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            return user.get();
        }
        throw new UserException("User Not Found "+ userId);
    }
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
