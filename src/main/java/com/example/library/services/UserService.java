package com.example.library.services;

import com.example.library.DTO.UserDTO;
import com.example.library.DTO.Id;
import com.example.library.exceptions.EmptyRepositoryException;
import com.example.library.exceptions.InvalidInputException;
import com.example.library.exceptions.UserAlreadyExistsException;
import com.example.library.entities.User;
import com.example.library.enums.Role;
import com.example.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User addUser(UserDTO userDTO) throws UserAlreadyExistsException {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("User with entered username (" + userDTO.getUsername()
                    + ") already exists.");
        }
        else {
            User user = mapDTOToUser(userDTO);
            return userRepository.save(user);
        }
    }

    public Iterable<User> getAllUsers() throws EmptyRepositoryException {
        if (userRepository.findAll().isEmpty()) {
            throw new EmptyRepositoryException("There are not any registered users.");
        }
        return userRepository.findAll();
    }

    public void deleteUser(Id id) throws InvalidInputException {
        if (!userRepository.existsById(id.getId())) {
            throw new InvalidInputException("There is no user with id: " + id.getId());
        }
        userRepository.deleteById(id.getId());
    }

    private User mapDTOToUser(UserDTO userDTO) {
        var user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setRole(Role.valueOf(userDTO.getRole()));
        user.setFullName(userDTO.getFullName());
        return user;
    }
}
