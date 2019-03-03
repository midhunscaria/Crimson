package com.example.crimson.crimson.Controller.BuilderClasses;

public class NewDueOneTime extends Sample {
    private String oneTimeName;
    private String oneTimeAmount;
    private String oneTimeEmailId;
    private String oneTimeUserID;
    public NewDueOneTime(String name, String amount, String email, String user)
    {
      this.oneTimeAmount=amount;
      this.oneTimeName=name;
      this.oneTimeEmailId=email;
      this.oneTimeUserID=user;
    }
    public String getName(){
        return oneTimeName;

    }
    public String getAmount()
    {
        return oneTimeAmount;

    }
    public String getPeriod()
    {
        return "0";
    }
    public String getUserId()
    {
        return oneTimeUserID;
    }
    public String getEmail_id()
    {
        return oneTimeEmailId;
    }

}

