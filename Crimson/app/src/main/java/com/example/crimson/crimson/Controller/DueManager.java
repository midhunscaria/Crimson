package com.example.crimson.crimson.Controller;

import com.example.crimson.crimson.Interfaces.DueAPI;

public class DueManager extends DueBridge{

    private String dueReceiverStr;
    private String dueAmountStr;

    /**
     * Bridge Pattern
     *
     * Due Manager extends the DueBridge and implements the generateResultString()
     * The One Time or Periodic information is transferre to DueBridge from here
     * and the dueAPI method to call is decided upon here.
     *
     *
     */

    public DueManager(String dueReceiverStr, String dueAmountStr, DueAPI dueAPI)
    {
        super(dueAPI);
        this.dueAmountStr = dueAmountStr;
        this.dueReceiverStr = dueReceiverStr;
    }

    public String generateResultString()
    {
        return dueAPI.generateDueString(this.dueReceiverStr, this.dueAmountStr);
    }
}
