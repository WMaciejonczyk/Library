package com.example.library.controllers;

import com.example.library.DTO.UserDTO;
import com.example.library.DTO.Id;
import com.example.library.exceptions.EmptyRepositoryException;
import com.example.library.exceptions.InvalidInputException;
import com.example.library.exceptions.UserAlreadyExistsException;
import com.example.library.entities.User;
import com.example.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody User addUser(@RequestBody UserDTO userDTO) throws ResponseStatusException {
        try {
            return userService.addUser(userDTO);
        } catch (UserAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    @GetMapping("/getAll")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody Iterable<User> getAllUsers() {
        try {
            return userService.getAllUsers();
        } catch (EmptyRepositoryException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    @DeleteMapping("/delete")
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody void deleteUser(@RequestBody Id id) {
        try {
            userService.deleteUser(id);
        } catch (InvalidInputException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }
}
