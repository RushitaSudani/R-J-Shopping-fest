package org.technous.validation.service;

import org.technous.validation.dto.LoginResponseDto;
import org.technous.validation.dto.RegisterDto;
import org.technous.validation.exception.UserException;
import org.technous.validation.model.User;

public interface Iuserservice {

    boolean isUsernameAvailable(String username);
    LoginResponseDto registerUser(RegisterDto registerDto);
    public User findUserById(Long userId) throws UserException;

}
