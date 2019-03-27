package com.example.crimson.crimson.Model;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.example.crimson.crimson.Controller.BuilderClasses.Sample;
import com.example.crimson.crimson.Controller.Fragments.dues_fragment;
import com.example.crimson.crimson.Controller.Fragments.expense_fragment;
import com.example.crimson.crimson.Controller.Fragments.goals_fragment;
import com.example.crimson.crimson.Controller.BuilderClasses.Dues;
import com.example.crimson.crimson.Controller.BuilderClasses.Expense;
import com.example.crimson.crimson.Controller.BuilderClasses.Goals;
import com.example.crimson.crimson.Controller.BuilderClasses.UserDetails;

import com.example.crimson.crimson.Controller.ServiceLocator.Service;
import com.example.crimson.crimson.Controller.ServiceLocator.ServiceLocator;
import com.example.crimson.crimson.Controller.ServiceLocator.UserProfileUpdateService;
import com.example.crimson.crimson.Utility.Util;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class DAO {

    public static DatabaseReference mDbRef = FirebaseDatabase.getInstance().getReference();
    public static DatabaseReference userProfileRef = mDbRef.child("User_Details");

    public static Task<Void> fb_push_task;

    public Task<Void> push_t;
    public static Service service;


    public static void pushGoals(Goals goals, DatabaseReference databaseReference) {

        service = ServiceLocator.getService("GoalsUpdateService");
        service.service(goals, databaseReference);

    }

    public static void pushExpenses(Expense expense, DatabaseReference databaseReference) {
        Handler handler = new Handler();

        expense_fragment.expense_push_task = databaseReference.child("Expenses").push().setValue(expense);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                expense_fragment.expense_push_task_flag = expense_fragment.expense_push_task.isSuccessful();
            }
        },1000);

    }

    public static void pushDues(Sample dues, DatabaseReference databaseReference, String category) {
        Handler handler = new Handler();
        dues_fragment.due_push_task = databaseReference.child("Dues").child(category).push().setValue(dues);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dues_fragment.due_push_task_flag = dues_fragment.due_push_task.isSuccessful();
            }
        },1000);

    }

    public static void pushUserProfile(UserDetails userDetails, DatabaseReference databaseReference) {

        service = ServiceLocator.getService("UserProfileUpdateService");
        service.service(userDetails, databaseReference);

    }

}