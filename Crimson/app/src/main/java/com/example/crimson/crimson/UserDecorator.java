package com.example.crimson.crimson;

public class UserDecorator implements User{

    User tempUser;

    public UserDecorator(User newUser){
        tempUser=newUser;
    }

    @Override
    public String createRegisteredUser() {

        return tempUser.createRegisteredUser();
    }
}
