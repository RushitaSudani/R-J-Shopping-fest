package org.technous.validation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.technous.validation.model.User;
import org.technous.validation.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/saveUser")
    public ResponseEntity<User> addUser(@RequestBody User user){
        User userdata = userService.saveUser(user);
        return new ResponseEntity<>(userdata, HttpStatus.OK);
    }


}
