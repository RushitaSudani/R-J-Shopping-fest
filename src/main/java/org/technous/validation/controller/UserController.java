package org.technous.validation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.technous.validation.dto.LoginRequest;
import org.technous.validation.dto.LoginResponseDto;
import org.technous.validation.dto.RegisterDto;
import org.technous.validation.model.User;
import org.technous.validation.service.Iuserservice;
import org.technous.validation.service.impl.AuthService;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class UserController {

//    @PostMapping("/register")
//    public ResponseEntity<LoginResponseDto> registerUser(@RequestBody RegisterDto registerDto) {
//        return ResponseEntity.ok(userService.registerUser(registerDto));
//    }
//    @PostMapping("/login")
//    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody LoginRequest loginRequest) {
//        LoginResponseDto loginResponseDto = authService.authenticateUser(loginRequest);
//        return ResponseEntity.ok(loginResponseDto);
//    }

    @Autowired
    private Iuserservice userService;
    private final AuthService authService;

    @Autowired
    public UserController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponseDto> registerUser(@RequestBody RegisterDto registerDto) {
        return ResponseEntity.ok(userService.registerUser(registerDto));
    }


//    @PostMapping("/login")
//    public ResponseEntity<LoginResponseDto> loginUserr(@RequestBody LoginRequest loginRequest) {
//        LoginResponseDto loginResponseDto = authService.authenticateUser(loginRequest);
//
//        if (loginResponseDto != null) {
//            return ResponseEntity.ok(loginResponseDto);
//        } else {
//
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody LoginRequest loginRequest) {
        // Authenticate user
        LoginResponseDto loginResponseDto = authService.authenticateUser(loginRequest);

        if (loginResponseDto != null) {
            // Get userId from loginResponseDto
            Long userId = loginResponseDto.getUserId();

            LoginResponseDto responseDto = new LoginResponseDto();
            responseDto.setId(loginResponseDto.getId());
            responseDto.setUsername(loginResponseDto.getUsername());
            responseDto.setEmail(loginResponseDto.getEmail());
            responseDto.setRole(loginResponseDto.getRole());
          //  responseDto.setUserId(userId);
            responseDto.setUserId(loginResponseDto.getId());
            return ResponseEntity.ok(responseDto);
        } else {
            //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credential");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
