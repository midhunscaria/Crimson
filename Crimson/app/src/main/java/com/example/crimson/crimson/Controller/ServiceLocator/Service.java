package com.example.crimson.crimson.Controller.ServiceLocator;

import com.google.firebase.database.DatabaseReference;

public interface Service {

    public void service(Object input, DatabaseReference mDbRef, String branchName);
    public String getName();
}
