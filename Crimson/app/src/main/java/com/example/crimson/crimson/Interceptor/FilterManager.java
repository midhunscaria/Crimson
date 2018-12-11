package com.example.crimson.crimson.Interceptor;

public class FilterManager {

    FilterChain filterChain;

    public FilterManager()
    {
        filterChain = new FilterChain();
    }

    public void addFilter(IFilter filter)
    {
        filterChain.addFilter(filter);
    }

    public void execute(String request)
    {
        filterChain.execute(request);
    }
}
