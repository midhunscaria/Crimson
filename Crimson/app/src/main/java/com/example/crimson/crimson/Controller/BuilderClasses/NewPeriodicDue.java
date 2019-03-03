package com.example.crimson.crimson.Controller.BuilderClasses;

public class NewPeriodicDue extends Sample {
    private String periodicName;
    private String periodicEmail_id;
    private String periodicAmount;
    private String periodicPeriod;
    private String periodicUser_identifier;
    private DueSingleton dueSingleton;
    public NewPeriodicDue(DueSingleton dueSingleton1)
    {
        this.dueSingleton=dueSingleton1;
        this.periodicName=dueSingleton.singleReceiverName;
        this.periodicAmount=dueSingleton.singleAmount;
        this.periodicEmail_id=dueSingleton.singleEmail;
        this.periodicUser_identifier=dueSingleton.singleUserID;
        this.periodicPeriod=dueSingleton.singlePeriod;
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

