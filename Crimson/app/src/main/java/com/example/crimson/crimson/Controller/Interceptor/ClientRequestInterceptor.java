package com.example.crimson.crimson.Controller.Interceptor;

public interface ClientRequestInterceptor
{
    public void onPreMarshaledRequest(ContextObjectInterface context);
    public void onPostMarshaledRequest(ContextObjectInterface context);
}
