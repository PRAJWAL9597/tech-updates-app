package com.notmine.tech_updates_app.repository;

import com.notmine.tech_updates_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // marks thsis a spring repo bean
public interface UserRepository extends JpaRepository<User, Long> {
        //spring data jpa will automatically create database methods for me
        //hehe
    
}

