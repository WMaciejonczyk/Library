package com.example.library.services;

import com.example.library.DTO.UserDTO;
import com.example.library.exceptions.EmptyRepositoryException;
import com.example.library.exceptions.InvalidInputException;
import com.example.library.exceptions.UserAlreadyExistsException;
import com.example.library.entities.User;
import com.example.library.enums.Role;
import com.example.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Service for managing users.
 * This service provides methods for adding, deleting, updating and retrieving users.
 */
@Service
public class UserService {

    /**
     * The repository for accessing users.
     */
    private final UserRepository userRepository;

    /**
     * The password encoder for password verification.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructs a new UserService with the specified user repository and password encoder.
     *
     * @param userRepository the repository for accessing users
     * @param passwordEncoder the password encoder for password verification
     */
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Adds a new user.
     *
     * @param userDTO the DTO of the user to add
     * @return the added user
     * @throws UserAlreadyExistsException if the user already exists
     * @throws InvalidInputException if the input is invalid
     */
    public User addUser(UserDTO userDTO) throws UserAlreadyExistsException, InvalidInputException {
        if (!Arrays.stream(Role.values()).toList().contains(Role.valueOf(userDTO.getRole()))) {
            throw new InvalidInputException("There is no role: " + userDTO.getRole() + ".");
        }
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("User with entered username (" + userDTO.getUsername()
                    + ") already exists.");
        }
        else {
            User user = mapDTOToUser(userDTO);
            return userRepository.save(user);
        }
    }

    /**
     * Retrieves all users.
     *
     * @return all users
     * @throws EmptyRepositoryException if the repository is empty
     */
    public Iterable<User> getAllUsers() throws EmptyRepositoryException {
        if (userRepository.findAll().isEmpty()) {
            throw new EmptyRepositoryException("There are not any registered users.");
        }
        return userRepository.findAll();
    }

    /**
     * Deletes a user.
     *
     * @param id the ID of the user to delete
     * @throws InvalidInputException if the input is invalid
     */
    public void deleteUser(int id) throws InvalidInputException {
        if (!userRepository.existsById(id)) {
            throw new InvalidInputException("There is no user with id: " + id + ".");
        }
        userRepository.deleteById(id);
    }

    /**
     * Updates a user.
     *
     * @param id the ID of the user to update
     * @param username the new username
     * @param role the new role
     * @param email the new email
     * @param fullName the new full name
     * @return the updated user
     * @throws InvalidInputException if the input is invalid
     */
    public User updateUser(int id, String username, String role, String email, String fullName) throws InvalidInputException {
        if (!userRepository.existsById(id)) {
            throw new InvalidInputException("There is no user with id: " + id + ".");
        }
        if (username == null && role == null && email == null && fullName == null) {
            throw new InvalidInputException("There is no info to update user with.");
        }
        User user = userRepository.findById(id).get();
        if (username != null) {
            user.setUsername(username);
        }
        if (role != null && Arrays.stream(Role.values()).toList().contains(Role.valueOf(role))) {
            user.setRole(Role.valueOf(role));
        }
        if (email != null && email.contains("@")) {
            user.setEmail(email);
        }
        if (fullName != null) {
            user.setFullName(fullName);
        }
        return userRepository.save(user);
    }

    /**
     * Maps a UserDTO to a User entity.
     *
     * @param userDTO the DTO to map
     * @return the mapped User entity
     */
    private User mapDTOToUser(UserDTO userDTO){
        var user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setRole(Role.valueOf(userDTO.getRole()));
        user.setFullName(userDTO.getFullName());
        return user;
    }
}
