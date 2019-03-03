package com.example.crimson.crimson.Controller.BuilderClasses;

public class DueSingleton {
    public String singleReceiverName;
    public String singleAmount;
    public String singlePeriod;
    public String singleCategory;
    public String singleEmail;
    public String singleUserID;
    public DueSingleton(String name, String amount, String period, String category, String email, String user){
        this.singleReceiverName=name;
        this.singleAmount=amount;
        this.singlePeriod=period;
        this.singleCategory=category;
        this.singleEmail=email;
        this.singleUserID=user;

    }
}
