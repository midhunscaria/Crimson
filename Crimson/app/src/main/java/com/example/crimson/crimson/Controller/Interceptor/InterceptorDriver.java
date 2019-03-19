package com.example.crimson.crimson.Controller.Interceptor;

import android.util.Log;

import com.example.crimson.crimson.Interfaces.Observer;

import java.io.IOException;

public class InterceptorDriver implements Observer {

    public ContextObjectInterface contextObject;
    public MarshaledRequest timer;

    public InterceptorDriver(ContextObjectInterface contextObject, MarshaledRequest timer)
    {
        this.contextObject = contextObject;
        this.timer = timer;
    }

    @Override
    public void update() throws IOException {
        timer.startTimer();
        Dispatcher.getInstance().iterate_list(this.contextObject, this.timer);
    }
}
