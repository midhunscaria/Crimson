package com.example.crimson.crimson.Interceptor;

public interface IFilter {
    public String getFilterName();
    public String processRequest(String username);
}
