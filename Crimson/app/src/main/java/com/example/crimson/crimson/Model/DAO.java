package com.example.crimson.crimson.Model;

import android.os.Handler;

import com.example.crimson.crimson.Controller.BuilderClasses.Sample;
import com.example.crimson.crimson.Controller.Fragments.dues_fragment;
import com.example.crimson.crimson.Controller.Fragments.expense_fragment;
import com.example.crimson.crimson.Controller.BuilderClasses.Expense;
import com.example.crimson.crimson.Controller.BuilderClasses.Goals;
import com.example.crimson.crimson.Controller.BuilderClasses.UserDetails;

import com.example.crimson.crimson.Controller.ServiceLocator.Service;
import com.example.crimson.crimson.Controller.ServiceLocator.ServiceLocator;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class DAO {

    public static Service service = ServiceLocator.getService("DBUpdateService");


    public static void pushGoals(Goals goals, DatabaseReference databaseReference) {
        service.service(goals, databaseReference, "Goals");
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
        service.service(userDetails, databaseReference, "User_Details");

    }

}