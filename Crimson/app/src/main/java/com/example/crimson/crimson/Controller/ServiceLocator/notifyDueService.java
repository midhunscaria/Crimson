package com.example.crimson.crimson.Controller.ServiceLocator;

import android.util.Log;

import com.example.crimson.crimson.Controller.BuilderClasses.NewDueOneTime;
import com.example.crimson.crimson.Controller.BuilderClasses.Sample;

public class notifyDueService implements EmailNotify {

    @Override
    public void emailnotify(Object object) {
        Log.i("Sending Email: ", "Notifying "+((NewDueOneTime)object).getEmail_id() +" that they owe you money!");
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }
}
