package com.example.crimson.crimson.Model;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.example.crimson.crimson.Controller.Fragments.dues_fragment;
import com.example.crimson.crimson.Controller.Fragments.expense_fragment;
import com.example.crimson.crimson.Controller.Fragments.goals_fragment;
import com.example.crimson.crimson.Controller.BuilderClasses.Dues;
import com.example.crimson.crimson.Controller.BuilderClasses.Expense;
import com.example.crimson.crimson.Controller.BuilderClasses.Goals;
import com.example.crimson.crimson.Controller.BuilderClasses.UserDetails;

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


    public static String nameStr, ageStr, occupationStr, salaryStr, subsStr, silverStr, goldStr, diamondStr, typeStr;

    public static void getUserProfileDetails(final String user_identifier) {
        mDbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String user_id_fb = ds.child("user_identifier").getValue(String.class);

                    if (user_id_fb.equals(user_identifier)) {
                        nameStr = ds.child("nameOfUser").getValue(String.class);
                        ageStr = ds.child("ageOfUser").getValue(String.class);
                        occupationStr = ds.child("occupationOfUser").getValue(String.class);
                        salaryStr = ds.child("annualIncomeofUser").getValue(String.class);
                        subsStr = ds.child("userType").getValue(String.class);

                        if (subsStr.equals("true")) {
                            subsStr = "Subscribed Member";

                            silverStr = ds.child("userTypeSilver").getValue(String.class);
                            goldStr = ds.child("userTypeGold").getValue(String.class);
                            diamondStr = ds.child("userTypeDiamond").getValue(String.class);

                            if (silverStr != null && silverStr.equals("true"))
                                typeStr = "Silver Subscription";
                            else if (goldStr != null && goldStr.equals("true"))
                                typeStr = "Gold Subscription";
                            else if (diamondStr != null && diamondStr.equals("true"))
                                typeStr = "Diamond Subscription";
                        } else {
                            subsStr = "Free Memeber";
                            typeStr = "";
                        }

                        break;
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static void pushGoals(Goals goals, DatabaseReference databaseReference) {
        Handler handler = new Handler();

        fb_push_task = databaseReference.child("Goals").push().setValue(goals);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                goals_fragment.push_task = fb_push_task.isSuccessful();
            }
        },1000);

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

    public static void pushDues(Dues dues, DatabaseReference databaseReference, String category) {
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
        Handler handler = new Handler();

        fb_push_task = databaseReference.child("User_Details").push().setValue(userDetails);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                goals_fragment.push_task = fb_push_task.isSuccessful();
            }
        },1000);

    }



}