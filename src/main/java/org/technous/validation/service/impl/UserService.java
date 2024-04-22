package org.technous.validation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.technous.validation.dto.LoginResponseDto;
import org.technous.validation.dto.RegisterDto;
import org.technous.validation.exception.UserException;
import org.technous.validation.model.Role;
import org.technous.validation.model.User;
import org.technous.validation.repository.RoleRepo;
import org.technous.validation.repository.UserRepository;
import org.technous.validation.service.Iuserservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements Iuserservice {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepo roleRepo;

    public LoginResponseDto registerUser(RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getEmail())) {
           throw new IllegalArgumentException("Email already exists");
        }
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(registerDto.getPassword());
        user.setPhone_no(registerDto.getPhone_no());

        List<Role> roleList = new ArrayList<>();
        roleList.add(roleRepo.findByName(registerDto.getRole()).orElseThrow(() -> new IllegalArgumentException("Role not found")));
        user.setRoles(roleList);

        User savedUser = userRepository.save(user);

        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setId(savedUser.getId());
        loginResponseDto.setEmail(savedUser.getEmail());
        loginResponseDto.setUsername(savedUser.getUsername());
        loginResponseDto.setRole(savedUser.getRoles().get(0).getName());

        return loginResponseDto;
    }
    public boolean isUsernameAvailable(String username) {
        return userRepository.findByUsername(username) == null;
    }
    public User findUserById(Long userId) throws UserException {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            return user.get();
        }
        throw new UserException("User Not Found "+ userId);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
