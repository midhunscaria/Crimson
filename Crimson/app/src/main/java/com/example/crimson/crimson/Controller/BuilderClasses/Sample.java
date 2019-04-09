package com.example.crimson.crimson.Controller.BuilderClasses;

public abstract class Sample {
    public abstract String getName();
    public abstract String getAmount();
    public abstract String getPeriod();
    public abstract String getUserId();
    public abstract String getEmail_id();

    public void creator(){
        this.getName();
        this.getAmount();
        this.getPeriod();
        this.getUserId();
        this.getEmail_id();
    }

}
