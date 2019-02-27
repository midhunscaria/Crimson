package com.example.crimson.crimson.Command;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

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
