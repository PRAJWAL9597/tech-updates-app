package com.notmine.tech_updates_app.controller;

import com.notmine.tech_updates_app.model.User;
import com.notmine.tech_updates_app.repository.UserRepository; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

   
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public User registerUser(@RequestBody User newUser) {
        
        User savedUser = userRepository.save(newUser);
        
        System.out.println("Saved user to database with ID: " + savedUser.getId());
        
        
        return savedUser;
    }

    //get  method

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
}
}
