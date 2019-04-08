package com.example.crimson.crimson.Controller.ServiceLocator;

import android.os.Handler;

import com.example.crimson.crimson.Controller.Fragments.goals_fragment;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

public class DBUpdateService implements Service {

    public static Task<Void> fb_push_task;

    @Override
    public void service(Object input, DatabaseReference mDbRef, String branchName) {
        Handler handler = new Handler();

        fb_push_task = mDbRef.child(branchName).push().setValue(input);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                goals_fragment.push_task = fb_push_task.isSuccessful();
            }
        },1000);
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }
}
