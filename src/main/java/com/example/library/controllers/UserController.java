package com.example.library.controllers;

import com.example.library.DTO.UserDTO;
import com.example.library.DTO.Id;
import com.example.library.entities.User;
import com.example.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public @ResponseBody User addUser(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    @GetMapping("/getAll")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/delete")
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody void deleteUser(@RequestBody Id id) {
        userService.deleteUser(id);
    }
}
