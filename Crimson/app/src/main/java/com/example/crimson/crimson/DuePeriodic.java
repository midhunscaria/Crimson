package com.example.crimson.crimson;

public class DuePeriodic implements DueAPI {

    public String duePeriodStr;

    public DuePeriodic(String duePeriodStr)
    {
        this.duePeriodStr = duePeriodStr;
    }

    public String generateDueString(String dueReceiverStr, String dueAmountSt)
    {
        return dueReceiverStr+","+dueAmountSt+","+this.duePeriodStr;
    }

}
