package com.example.crimson.crimson.Controller;

import com.example.crimson.crimson.Interfaces.User;

public class UserDecorator implements User {

    User tempUser;

    public UserDecorator(User newUser){
        tempUser=newUser;
    }

    @Override
    public String createRegisteredUser() {

        return tempUser.createRegisteredUser();
    }
}
