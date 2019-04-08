package com.example.crimson.crimson.Controller.Interceptor;

import android.util.Log;

import com.example.crimson.crimson.Interfaces.Observer;

import java.io.IOException;

public class InterceptorDriver implements Observer {

    //Concrete Framework

    public Dispatcher dispatcher;
    public ContextObjectInterface expenseContextObject;
    public MarshaledRequest timer;

    public InterceptorDriver()
    {
        //Concrete Framework creates Dispatcher
        dispatcher = Dispatcher.getInstance();

        //Concrete Framework creates Context Object
        this.expenseContextObject = new ContextObject();

        //Initial Context Object Configuration
        this.expenseContextObject.setHost("http://10.102.24.215");
        this.expenseContextObject.setMethod("GET");
        this.expenseContextObject.setPort(8080);

        this.timer = new Timer();

        //Create Application
        InterceptorApplication application = new InterceptorApplication();
        application.attachInterceptor(new DataInterceptor());

    }

    @Override
    public void update() throws IOException {

        //Event based. Triggers Interceptors
        timer.startTimer();
        dispatcher.iterate_list(this.expenseContextObject, this.timer);
    }
}
