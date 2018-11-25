package com.example.crimson.crimson;

public interface Subject
{
    public void register(benefit_fragment observer);
    public void unregister(Observer observer);
    public void notifyObservers();
}
