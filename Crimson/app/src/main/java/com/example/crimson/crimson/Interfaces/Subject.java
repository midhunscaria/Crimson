package com.example.crimson.crimson.Interfaces;

import java.io.IOException;

/**
 * Observer Pattern
 *
 * Subject interface is implemented by the Subject.
 *
 */
public interface Subject {
    public void register(Observer o);
    public void unregister(Observer o);
    public void notifyObservers() throws IOException;
}
