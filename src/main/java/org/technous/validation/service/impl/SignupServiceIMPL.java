package org.technous.validation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.technous.validation.model.SignUp;
import org.technous.validation.repository.SignUpRepository;

@Service
public class SignupServiceIMPL{

    @Autowired
    private SignUpRepository signUpRepository;

    public SignUp save(SignUp signUp) {
        return signUpRepository.save(signUp);
    }

    public SignUp findByUsername(String email) {
        return signUpRepository.findByEmail(email);
    }

}
