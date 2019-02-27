package com.example.crimson.crimson.Controller.Interceptor;

public interface UnmarshaledRequest
{
    public String getHost();
    public void setHost(String host);
    public int getPort();
    public void setPort(int port);
    public String getMethod();
    public void setMethod(String method);
}
