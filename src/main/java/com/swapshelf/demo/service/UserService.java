package com.swapshelf.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swapshelf.demo.domain.User;
import com.swapshelf.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> register(User user) {
        // Check if user already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return Optional.empty(); // User already exists
        }
        return Optional.of(userRepository.save(user)); // Register new user
    }

    public Optional<User> login(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(u -> u.getPassword().equals(password));
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
