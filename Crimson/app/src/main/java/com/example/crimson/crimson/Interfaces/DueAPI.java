package com.example.crimson.crimson.Interfaces;


/**
 * Bridge Pattern
 *
 * Defines an Interface for the bridge pattern implementation.
 * generateDueString() generates the string that helps in generating an object
 * for a due.
 *
 */


public interface DueAPI {

    public String generateDueString(String dueReceiverStr, String dueAmountStr);

}
