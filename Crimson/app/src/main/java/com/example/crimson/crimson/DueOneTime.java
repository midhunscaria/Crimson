package com.example.crimson.crimson;

public class DueOneTime implements DueAPI {

    //new DueManager(dueReceiverStr, dueAmountStr, new DueOneTime(email));

    public String dueRecieverEmailStr;

    public DueOneTime(String dueRecieverEmail)
    {
        this.dueRecieverEmailStr = dueRecieverEmail;
    }

    public String generateDueString(String dueReceiverStr, String dueAmountSt)
    {
        return new String(dueReceiverStr+","+dueAmountSt+","+this.dueRecieverEmailStr);
    }
}
