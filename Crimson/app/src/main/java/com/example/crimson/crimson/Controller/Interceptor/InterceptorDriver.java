package com.example.crimson.crimson.Controller.Interceptor;


import com.example.crimson.crimson.Interfaces.Observer;
import com.example.crimson.crimson.Utility.Util;

import java.io.IOException;

public class InterceptorDriver implements Observer {

    //Concrete Framework

    public Dispatcher dispatcher;
    public ContextObjectInterface expenseContextObject;

    public InterceptorDriver()
    {
        //Concrete Framework creates Dispatcher
        dispatcher = Dispatcher.getInstance();


    }

    @Override
    public void update() throws IOException {


        //Concrete Framework creates Context Object
        this.expenseContextObject = new ContextObject();

        //Initial Context Object Configuration
        this.expenseContextObject.setHost(Util.getIp());
        this.expenseContextObject.setMethod("GET");
        this.expenseContextObject.setPort(8080);

        //Event based. Triggers Interceptors
        dispatcher.iterate_list(this.expenseContextObject);
    }
}
