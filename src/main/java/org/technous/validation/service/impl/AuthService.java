package org.technous.validation.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.technous.validation.dto.LoginRequest;
import org.technous.validation.dto.LoginResponseDto;
import org.technous.validation.repository.RoleRepo;
import org.technous.validation.repository.UserRepository;

import java.util.List;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepo roleRepo;

    public LoginResponseDto authenticateUser(LoginRequest loginRequest) {
        List<Object[]> list = userRepository.getLoginDetail(loginRequest.getUsername(),
                loginRequest.getPassword());

        LoginResponseDto loginResponseDto = null;

        if (!list.isEmpty()) {
            Object[] user = list.get(0);
            if (user != null) {
                loginResponseDto = new LoginResponseDto();
                loginResponseDto.setId((long) user[0]);
                loginResponseDto.setUsername((String) user[1]);
                loginResponseDto.setEmail((String) user[2]);
                loginResponseDto.setRole((String) user[3]);
            }
        }
        return loginResponseDto;
    }
}
