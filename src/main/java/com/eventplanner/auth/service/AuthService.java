package com.eventplanner.auth.service;

import java.util.List;

import com.eventplanner.auth.dto.Token;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eventplanner.auth.dto.AuthRequest;
import com.eventplanner.auth.entity.UserCredential;

@Service
public class AuthService {

    @Autowired
    private com.eventplanner.auth.repository.UserCredentialRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public ResponseEntity<?> getAllUsers() {
        List<UserCredential> list = repository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    public UserCredential saveUser(UserCredential credential) {
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        UserCredential user = repository.save(credential);
        Token token = generateToken(
                new AuthRequest(credential.getUsername(), credential.getPassword(), credential.getRole()));

        user.setToken(token.getToken());
        repository.save(user);
        return user;
    }

    public Token generateToken(AuthRequest authRequest) {
        return jwtService.generateToken(authRequest);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

}