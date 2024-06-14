package com.example.library.services;

import com.example.library.DTO.LoginForm;
import com.example.library.entities.User;
import com.example.library.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Service for managing user login.
 * This service provides a method for user login which includes password verification and JWT token generation.
 */
@Service
public class LoginService {

    /**
     * The repository for accessing users.
     */
    private final UserRepository userRepository;

    /**
     * The password encoder for password verification.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * The key used for JWT (JSON Web Token) generation.
     */
    @Value("${jwt.token.key}")
    private String key;

    /**
     * Constructs a new LoginService with the specified user repository and password encoder.
     *
     * @param userRepository the repository for accessing users
     * @param passwordEncoder the password encoder for password verification
     */
    @Autowired
    public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Logs in a user.
     * This method verifies the user's password and generates a JWT token if the password is correct.
     *
     * @param loginForm the form containing the user's login information
     * @return the JWT token if the password is correct, or null if the password is incorrect
     */
    public String userLogin(LoginForm loginForm) {
        User user = userRepository.findByUsername(loginForm.getLogin()).get();
        String password = user.getPassword();
        if (passwordEncoder.matches(loginForm.getPassword(), password)) {
            long timeMillis = System.currentTimeMillis();
            String token = Jwts.builder()
                    .issuedAt(new Date(timeMillis))
                    .expiration(new Date(timeMillis + 60 * 60 * 1000))
                    .claim("id", user.getId())
                    .claim("role", user.getRole())
                    .signWith(SignatureAlgorithm.HS256, key)
                    .compact();
            return token;
        } else {
            return null;
        }
    }
}
