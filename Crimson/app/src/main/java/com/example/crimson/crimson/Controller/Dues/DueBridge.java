package com.example.crimson.crimson.Controller.Dues;

import com.example.crimson.crimson.Interfaces.DueAPI;

public abstract class DueBridge {

    /**
     *Bridge Pattern
     *
     * This class is the Bridge.
     *
     * The bridge holds the information about what type of due
     * record needs to be generated.
     *
     * The generateResultString() method is abstract. The Manager class
     * that extends this Bridge implements the method and the dueAPI
     * helps generate the required string as per the correct type.
     *
     */

    protected DueAPI dueAPI;

    public DueBridge(DueAPI dueAPI)
    {
        this.dueAPI = dueAPI;
    }

    public abstract String generateResultString();
}
