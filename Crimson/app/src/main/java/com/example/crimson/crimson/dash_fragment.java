package com.example.crimson.crimson;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class dash_fragment extends Fragment {

    public ArrayList<String> expenses;
    public ArrayAdapter<String> adapter;

    public ListView dashboardExpenseList;

    public View parentHolder;

    public DatabaseReference mDbRef = FirebaseDatabase.getInstance().getReference();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        parentHolder = inflater.inflate(R.layout.fragment_dash_fragment, container, false);

        dashboardExpenseList = (ListView)parentHolder.findViewById(R.id.dashboard_expense_list);

        mDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return parentHolder;
    }

    public void showData(DataSnapshot dataSnapshot)
    {
        expenses = new ArrayList<String>();

        for(DataSnapshot snap : dataSnapshot.getChildren())
        {
//            Expense expense_object = new Expense.Builder().setAmount(snap.child("Expenses").getValue(Expense.class).getAmount()).setCategory(snap.child("Expenses").getValue(Expense.class).getCategory()).setPlace(snap.child("Expenses").getValue(Expense.class).getPlace()).setUserIdentifier(snap.child("Expenses").getValue(Expense.class).getUser_identifier()).create();
//            expenses.add(Double.toString(expense_object.getAmount()));
//            expenses.add(expense_object.getCategory());
//            expenses.add(expense_object.getPlace());
//            expenses.add(expense_object.getUser_identifier());
//
//            Toast.makeText(parentHolder.getContext(),""+ Arrays.toString(expenses.toArray()), Toast.LENGTH_LONG).show();
        }
    }

}
