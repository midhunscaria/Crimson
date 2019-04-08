package com.example.crimson.crimson.Controller.Interceptor;

public class InterceptorApplication {

    //Application attaches Concrete Interceptor with Dispathcer
    public void attachInterceptor(ClientRequestInterceptor interceptor)
    {
        Dispatcher.getInstance().register(interceptor);
    }
}
