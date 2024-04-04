package org.technous.validation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.technous.validation.model.SignUp;
import org.technous.validation.service.impl.SignupServiceIMPL;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class SignupController {

    @Autowired
    private SignupServiceIMPL signupService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody SignUp user) {
        SignUp existingUser = signupService.findByUsername(user.getEmail());
        if (existingUser != null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        signupService.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody SignUp user) {
        SignUp existingUser = signupService.findByUsername(user.getEmail());
        if (existingUser == null || !existingUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }
        return ResponseEntity.ok("Login successful");
    }
}
