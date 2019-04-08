package com.example.crimson.crimson.Controller.ServiceLocator;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.example.crimson.crimson.Controller.Fragments.goals_fragment;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class UserProfileUpdateService implements Service {

    public static Task<Void> fb_push_task;

    @Override
    public void service(Object input, DatabaseReference databaseReference, String branchName) {
//        Handler handler = new Handler();
//
//        fb_push_task = databaseReference.child("User_Details").push().setValue(input);
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                goals_fragment.push_task = fb_push_task.isSuccessful();
//            }
//        },1000);

    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }
}
