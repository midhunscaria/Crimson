package com.example.crimson.crimson.Controller.Interceptor;

public interface MarshaledRequest
{
    public void startTimer();
    public void endTimer();
    public String calculateLatency();
}
