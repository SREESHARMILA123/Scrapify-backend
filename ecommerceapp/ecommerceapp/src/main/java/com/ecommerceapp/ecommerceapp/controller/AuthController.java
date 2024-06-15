package com.ecommerceapp.ecommerceapp.controller;

import com.ecommerceapp.ecommerceapp.model.User;
import com.ecommerceapp.ecommerceapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import org.springframework.web.bind.annotation.CrossOrigin;


@RestController
@RequestMapping("/api/auth")
public class AuthController {


    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        Optional<User> existingUser = userService.findByUsername(user.getUsername());
        if (existingUser.isPresent() && existingUser.get().getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok(existingUser.get().getId());
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        // Assuming UserService has a method to save user data
        User newUser = userService.save(user);
        return ResponseEntity.ok(newUser);
    }

}