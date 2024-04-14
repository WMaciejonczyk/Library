package com.example.library.controllers;

import com.example.library.DTO.UserDTO;
import com.example.library.exceptions.EmptyRepositoryException;
import com.example.library.exceptions.InvalidInputException;
import com.example.library.exceptions.UserAlreadyExistsException;
import com.example.library.entities.User;
import com.example.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * Controller for managing users.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    /**
     * Constructor for UserController.
     *
     * @param userService the service to handle user operations
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint to add a new user.
     *
     * @param userDTO the user data transfer object
     * @return the added user
     * @throws ResponseStatusException if parameters of the user are invalid or the user already exists
     */
    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody User addUser(@RequestBody UserDTO userDTO) throws ResponseStatusException {
        try {
            return userService.addUser(userDTO);
        } catch (InvalidInputException | UserAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    /**
     * Endpoint to get all users.
     *
     * @return all users
     * @throws ResponseStatusException if the user repository is empty
     */
    @GetMapping("/getAll")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody Iterable<User> getAllUsers() {
        try {
            return userService.getAllUsers();
        } catch (EmptyRepositoryException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    /**
     * Endpoint to delete a user by id.
     *
     * @param id the id of the user to delete
     * @throws ResponseStatusException if the id is invalid
     */
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody void deleteUser(@PathVariable String id) {
        try {
            userService.deleteUser(Integer.parseInt(id));
        } catch (InvalidInputException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    /**
     * Endpoint to update a user.
     *
     * @param id the id of the user to update
     * @param username the new username of the user
     * @param role the new role of the user
     * @param email the new email of the user
     * @param fullName the new full name of the user
     * @return the updated user
     * @throws ResponseStatusException if the id or any of the parameters are invalid
     */
    @PostMapping("/update/{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody User updateUser(@PathVariable String id,
                                         @RequestParam(value = "username", required = false) String username,
                                         @RequestParam(value = "role", required = false) String role,
                                         @RequestParam(value = "email", required = false) String email,
                                         @RequestParam(value = "fullName", required = false) String fullName) {
        try {
            return userService.updateUser(Integer.parseInt(id), username, role, email, fullName);
        } catch (InvalidInputException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }
}
