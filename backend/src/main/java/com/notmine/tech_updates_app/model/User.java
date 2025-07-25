package com.notmine.tech_updates_app.model;

public class User {
        private long id;
        private String userName;
        private String email;
        private String passwordHash;

        //constructor
        User(){}
        User(long id,String userName,String email,String passwordHash){
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

