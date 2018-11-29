package com.example.crimson.crimson.Controller;

import com.example.crimson.crimson.Interfaces.DueAPI;

public abstract class DueBridge {

    /**
     *Bridge Pattern
     *
     * The bridge holds the information about what type of due
     * record needs to be generated.
     *
     */

    protected DueAPI dueAPI;

    public DueBridge(DueAPI dueAPI)
    {
        this.dueAPI = dueAPI;
    }

    public abstract String generateResultString();
}
