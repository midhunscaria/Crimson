package com.example.crimson.crimson.Command;

import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;



public class Services
{
    public final String url = "http://10.54.65.79:8080/traffic.json";

    public void generateReport()
    {
        Log.i("ReportGeneration", "Generated Report");
    }

    public void generateLoadBalanceService() throws IOException {

        //GET traffic from servers
        //increase traffic count


    }
}
