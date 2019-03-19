package com.example.crimson.crimson.Controller.Interceptor;

import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.ArrayList;

public class Dispatcher
{

    public ArrayList<ClientRequestInterceptor> dispatcherList = new ArrayList<ClientRequestInterceptor>();
    static Dispatcher dispatcher = null;

    private Dispatcher()
    {
    }

    public static Dispatcher getInstance()
    {
        if(dispatcher == null)
        {
            dispatcher = new Dispatcher();
        }

        return dispatcher;
    }

    public void register(ClientRequestInterceptor interceptor)
    {
        dispatcherList.add(interceptor);
    }

    public void remove(ClientRequestInterceptor interceptor)
    {
        if(dispatcherList.contains(interceptor))
            dispatcherList.remove(interceptor);
    }

    public void iterate_list(ContextObjectInterface contextObject, MarshaledRequest marshaledRequest)
    {
        for(ClientRequestInterceptor interceptor : dispatcherList)
        {
            interceptor.onPreMarshaledRequest(contextObject);
            interceptor.onPostMarshaledRequest(marshaledRequest);
        }
    }
}
