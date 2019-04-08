package com.example.crimson.crimson.Controller.ServiceLocator;

import android.util.Log;

import com.example.crimson.crimson.Controller.BuilderClasses.Dues;
import com.google.firebase.database.DatabaseReference;

public class notifyDueService implements Service {
    @Override
    public void service(Object input, DatabaseReference mDbRef, String branchName) {
        Log.i("Sending Email: ", "Notifying "+((Dues)input).getEmail_id()+" that a new "+branchName+" due was added!");
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }
}
