package com.learn.rest_crud.controller;

import com.learn.rest_crud.model.User;
import com.learn.rest_crud.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class AuthController {

    private UserService userService;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/login")
    public String login() {
        return "Login successful";
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {

        return userService.saveUser(user);
    }
}
