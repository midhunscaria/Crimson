package com.example.crimson.crimson.Controller.Interceptor;

public interface ClientRequestInterceptor
{
    public void onPreMarshaledRequest(UnmarshaledRequest context);
    public void onPostMarshaledRequest(MarshaledRequest context);
}
