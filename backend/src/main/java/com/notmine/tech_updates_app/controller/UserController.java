package com.notmine.tech_updates_app.controller;

import com.notmine.tech_updates_app.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/users")
public class UserController {
        @PostMapping("/register")
        public String registerUser(@RequestBody User newUser) {
            System.out.println("Recived Registration Request for : "+newUser.getUserName());
            System.out.println("Email"+ newUser.getEmail());

            return "User "+newUser.getUserName()+" registered successfully";
        }
        

}
