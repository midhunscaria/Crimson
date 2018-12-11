package com.example.crimson.crimson.Interceptor;

public class InputIntegrityFilter implements IFilter{

    public String filterName;

    public InputIntegrityFilter(String filterName)
    {
        this.filterName = filterName;
    }

    @Override
    public String getFilterName()
    {
        return this.filterName;
    }

    @Override
    public String processRequest(String username) {

        if(username.contains("@gmail.com"))
        {
            return "Email is in correct format!";
        }

        return "Email is not in correct format!";
    }
}
