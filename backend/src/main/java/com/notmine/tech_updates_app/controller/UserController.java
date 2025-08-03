package com.notmine.tech_updates_app.controller;

import com.notmine.tech_updates_app.model.User;
import com.notmine.tech_updates_app.repository.UserRepository;
import com.notmine.tech_updates_app.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Registration endpoint hashes the password before saving
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User newUser) {
        if(userRepository.findByEmail(newUser.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already in use");
        }
        // Hash password before saving
        String hashedPassword = PasswordUtil.hashPassword(newUser.getPasswordHash());
        newUser.setPasswordHash(hashedPassword);

        User savedUser = userRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    // Login endpoint verifies password hash
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOpt = userRepository.findByEmail(loginRequest.getEmail());
        if(userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
        User user = userOpt.get();

        // Check password hash match
        if(PasswordUtil.matches(loginRequest.getPassword(), user.getPasswordHash())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
}

// DTO for login request
class LoginRequest {
    private String email;
    private String password;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
