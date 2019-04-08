package com.example.crimson.crimson.Controller.Interceptor;

public interface ContextObjectInterface
{
    public String getHost();
    public void setHost(String host);
    public int getPort();
    public void setPort(int port);
    public int getLoadBalancedPort();
    public void setLoadBalancedPort(int port);
    public String getMethod();
    public void setMethod(String method);
}
