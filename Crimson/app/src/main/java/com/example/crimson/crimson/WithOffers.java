package com.example.crimson.crimson;

public class WithOffers extends UserDecorator {

    public WithOffers(User newUser) {
        super(newUser);
    }

    public String createRegisteredUser(){
        return "Subscription Completed! Your benefits have been added!";
    }
}
