package com.example.crimson.crimson;

public abstract class DueBridge {

    protected DueAPI dueAPI;

    public DueBridge(DueAPI dueAPI)
    {
        this.dueAPI = dueAPI;
    }

    public abstract String generateResultString();
}
