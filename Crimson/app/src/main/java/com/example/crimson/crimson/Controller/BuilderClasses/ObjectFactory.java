package com.example.crimson.crimson.Controller.BuilderClasses;

public class ObjectFactory {
    public static Sample getObject(String name, String amount, String period, String userID, String email,String type)
    {

        if ("One Time".equalsIgnoreCase(type)) return new NewDueOneTime(name, amount,email,userID);
        else if("Periodic".equalsIgnoreCase(type))return new NewPeriodicDue(name,amount,email,period,userID);
        return null;
    }
}

