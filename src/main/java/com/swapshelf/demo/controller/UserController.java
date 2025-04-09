package com.swapshelf.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.swapshelf.demo.domain.User;
import com.swapshelf.demo.service.UserService;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    // ========== Register ==========
    @PostMapping({"/api/users/register", "/auth/register"})
    public ResponseEntity<?> register(@RequestBody User user) {
        Optional<User> registeredUser = userService.register(user);
        if (registeredUser.isPresent()) {
            return ResponseEntity.ok(registeredUser.get());
        } else {
            return ResponseEntity.badRequest().body("Email already exists");
        }
    }

    // ========== Login ==========
    @PostMapping({"/api/users/login", "/auth/login"})
    public ResponseEntity<?> login(@RequestBody User loginUser) {
        Optional<User> user = userService.login(loginUser.getEmail(), loginUser.getPassword());
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }

    // ========== Get user by email ==========
    @GetMapping({"/api/users/{email}", "/auth/{email}"})
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userService.findByEmail(email);
        return user.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
