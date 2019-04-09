package com.example.crimson.crimson.Controller.Interceptor;

import java.util.ArrayList;
import android.os.Handler;

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

    public void iterate_list(final ContextObjectInterface contextObject)
    {
        for(final ClientRequestInterceptor interceptor : dispatcherList)
        {
            interceptor.onPreMarshaledRequest(contextObject);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    interceptor.onPostMarshaledRequest(contextObject);
                }
            }, 3000);

        }
    }
}
