package com.notmine.tech_updates_app.controller;

import com.notmine.tech_updates_app.model.User;
import com.notmine.tech_updates_app.repository.UserRepository; 
import com.notmine.tech_updates_app.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.Optional;

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

    //   @PostMapping("/login")
    // public ResponseEntity<String> login(@RequestBody LoginRequest request){
    //     Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
    //     if (userOpt.isPresent()){
    //         User user = userOpt.get();
    //     }
    // }

    //get  method

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();

  

  
}
}
