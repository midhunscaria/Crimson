package com.example.crimson.crimson.Controller.Interceptor;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Timer implements  MarshaledRequest {

   public DateFormat dateFormat;
   public Date date;
   public String startTime, endTime;

    @Override
    public void startTimer() {
        dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        date = new Date();
        startTime = dateFormat.format(date).substring(17, 19); //17 to 19
        Log.i("Starting Time: ", startTime);
    }

    @Override
    public void endTimer() {
        dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        date = new Date();
        endTime = dateFormat.format(date).substring(17, 19);
        Log.i("Ending Time: ", endTime);
    }

    @Override
    public String calculateLatency() {
        return new String(""+(Integer.parseInt(endTime) - Integer.parseInt(startTime)));
    }
}
