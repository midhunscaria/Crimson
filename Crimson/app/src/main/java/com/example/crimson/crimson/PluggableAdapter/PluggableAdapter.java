package com.example.crimson.crimson.PluggableAdapter;

import java.lang.reflect.Method;

public class PluggableAdapter
{
    public Object adapt;

    public PluggableAdapter(Object adapt)
    {
        this.adapt = adapt;
    }

    public String generateReport() throws Exception {
        Class c = this.adapt.getClass();
        Method method = c.getDeclaredMethod("makenoise");

        return method.invoke(this.adapt).toString();
    }
}