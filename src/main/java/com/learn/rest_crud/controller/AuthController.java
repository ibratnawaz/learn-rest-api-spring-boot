package com.learn.rest_crud.controller;

import com.learn.rest_crud.model.User;
import com.learn.rest_crud.model.UserImpl;
import com.learn.rest_crud.service.JwtService;
import com.learn.rest_crud.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class AuthController {

    private UserService userService;

    private AuthenticationManager authenticationManager;

    private JwtService jwtService;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) throws Exception {
        String input = (user.getEmail() != null)
                ? user.getEmail()
                : user.getUsername();

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(input, user.getPassword()));

        if (authentication.isAuthenticated()) {
            UserImpl userDetails = (UserImpl) authentication.getPrincipal();
            return jwtService.generateToken(userDetails.getUsername());
        } else {
            throw new Exception("Login Failed");
        }
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.saveUser(user);
    }
}
