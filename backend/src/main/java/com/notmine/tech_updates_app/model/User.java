package com.notmine.tech_updates_app.model;

import jakarta.persistence.*;

@Entity //make this class sdaatabase entity
@Table(name = "users") //specifies the table name in database

public class User {
    @Id //make this fieldd primary
    @GeneratedValue(strategy = GenerationType.IDENTITY) //tells database to auto genetrate this ID

        private long id;
        private String userName;
        private String email;
        private String passwordHash;

        //constructor
       public User(){}
       public User(long id,String userName,String email,String passwordHash){
            this.id = id;
            this.userName = userName;
            this.email = email;
            this.passwordHash = passwordHash;
        }

        //getter and setter 

        public long getId(){return id;}
        public void setId(long id){this.id = id;}
        
        public String getUserName(){return userName;}
        public void setUserName(String userName){this.userName = userName;}

        public String getEmail(){return email;}
        public void setEmail(String email){this.email = email;}

        public String getPasswordHash(){return passwordHash;}
        public void setPasswordHash(String passwordHash){this.passwordHash = passwordHash;}


}

