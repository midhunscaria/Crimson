package com.example.crimson.crimson;

public class DueManager extends DueBridge{

    private String dueReceiverStr;
    private String dueAmountStr;

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
