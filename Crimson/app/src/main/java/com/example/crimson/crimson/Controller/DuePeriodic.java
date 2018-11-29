package com.example.crimson.crimson.Controller;

import com.example.crimson.crimson.Interfaces.DueAPI;

public class DuePeriodic implements DueAPI {

    /**
     * Bridge Pattern
     *
     * Used to generate the required due string for periodic type of dues.
     *
     */

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
