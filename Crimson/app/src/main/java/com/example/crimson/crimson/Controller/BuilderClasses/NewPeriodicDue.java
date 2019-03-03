package com.example.crimson.crimson.Controller.BuilderClasses;

public class NewPeriodicDue extends Sample {
    private String periodicName;
    private String periodicEmail_id;
    private String periodicAmount;
    private String periodicPeriod;
    private String periodicUser_identifier;
    public NewPeriodicDue(String name, String amount, String email, String period, String user)
    {
        this.periodicName=name;
        this.periodicAmount=amount;
        this.periodicEmail_id=email;
        this.periodicUser_identifier=user;
        this.periodicPeriod=period;
    }
    public String getName()
    {
        return periodicName;
    }
    public String getAmount(){
        return periodicAmount;
    }
    public String getPeriod()
    {
        return periodicPeriod;
    }
    public String getUserId()
    {
        return periodicUser_identifier;
    }
    public String getEmail_id(){
        return periodicEmail_id;
    }
}
