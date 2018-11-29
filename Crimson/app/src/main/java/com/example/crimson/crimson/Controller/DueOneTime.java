package com.example.crimson.crimson.Controller;

import com.example.crimson.crimson.Interfaces.DueAPI;

public class DueOneTime implements DueAPI {

    public String dueRecieverEmailStr;

    public DueOneTime(String dueRecieverEmail)
    {
        this.dueRecieverEmailStr = dueRecieverEmail;
    }

    public String generateDueString(String dueReceiverStr, String dueAmountSt)
    {
        return dueReceiverStr+","+dueAmountSt+","+this.dueRecieverEmailStr;
    }
}
