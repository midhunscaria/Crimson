package com.example.crimson.crimson.Interfaces;

import java.io.IOException;

/**
 * Observer Pattern
 *
 * Observer interface is implemented by the Observer.
 * The Observer makes changes when a change occurs in the subject.
 * In this case, the observer updates the existing Pie Chart, reflecting
 * the new expense that was added.
 *
 */

public interface Observer {

    public void update() throws IOException;

}
