package com.example.library.controllers;

import com.example.library.DTO.LoginForm;
import com.example.library.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing user login.
 */
@RestController
public class LoginController {

    private final LoginService loginService;

    /**
     * Constructor for LoginController.
     *
     * @param loginService the service to handle login operations
     */
    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * Endpoint to log in a user.
     *
     * @param loginForm the login form data transfer object
     * @return a ResponseEntity with a token if login is successful, or an error message if not
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginForm loginForm) {
        String token = loginService.userLogin(loginForm);
        if (token == null) {
            return new ResponseEntity<>("Wrong login or password", HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
    }
}
