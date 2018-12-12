package com.example.crimson.crimson.Interceptor;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class FilterChain {

        public List<IFilter> filterChain = new ArrayList<IFilter>();

        public String response;

        public void addFilter(IFilter filter)
        {
            filterChain.add(filter);
        }

        public void execute(String request)
        {
            for(IFilter filter : filterChain)
            {
                response = filter.processRequest(request);

                Log.i(filter.getFilterName(), response);
            }
        }

}
