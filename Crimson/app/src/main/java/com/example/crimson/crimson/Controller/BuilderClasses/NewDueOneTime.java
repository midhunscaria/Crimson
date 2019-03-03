package com.example.crimson.crimson.Controller.BuilderClasses;

public class NewDueOneTime extends Sample {
    private String oneTimeName;
    private String oneTimeAmount;
    private String oneTimeEmailId;
    private String oneTimeUserID;
    private DueSingleton dueSingleton;
    public NewDueOneTime(DueSingleton dueSingleton1)
    {
      this.dueSingleton=dueSingleton1;
      this.oneTimeAmount=dueSingleton.singleAmount;
      this.oneTimeName=dueSingleton.singleReceiverName;
      this.oneTimeEmailId=dueSingleton.singleEmail;
      this.oneTimeUserID=dueSingleton.singleUserID;

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

