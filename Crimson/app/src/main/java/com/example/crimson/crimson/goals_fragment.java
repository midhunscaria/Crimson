package com.example.crimson.crimson;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Handler;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class goals_fragment extends Fragment {

    public View parentHolder;
    public EditText goalTarget;
    public EditText goalAmount;
    public EditText goalPeriod;
    public Button creategoal;
    boolean task_successful;
    public Goals goal;
    public FirebaseAuth mAuth;
    public DatabaseReference mDbRef = FirebaseDatabase.getInstance().getReference();
    dash_fragment dash_fragment_object;
    public String goalTargetString;
    public String goalAmountString;
    public String goalPeriodString;

    public Handler handler;
    public Task<Void> push_task;

    public goals_fragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final Handler handler = new Handler();

        parentHolder = inflater.inflate(R.layout.fragment_goals_fragment, container, false);
        goalTarget = (EditText) parentHolder.findViewById(R.id.goalsTarget);
        goalAmount = (EditText) parentHolder.findViewById(R.id.goalsAmount);
        goalPeriod = (EditText) parentHolder.findViewById(R.id.goalsPeriod);
        creategoal = (Button) parentHolder.findViewById(R.id.createGoals);

        creategoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task_successful = false;
                goalTargetString= goalTarget.getText().toString();
                goalAmountString=goalAmount.getText().toString();
                goalPeriodString=goalPeriod.getText().toString();

                if ((TextUtils.isEmpty(goalTargetString)) || (TextUtils.isEmpty(goalAmountString)|| !TextUtils.isDigitsOnly(goalAmountString)) || (TextUtils.isEmpty(goalPeriodString)|| !TextUtils.isDigitsOnly(goalPeriodString))) {
                    Toast.makeText(parentHolder.getContext(), "Enter All Details", Toast.LENGTH_LONG).show();
                } else {
                    goal = new Goals.Builder().setTarget(goalTargetString).setAmount(Double.parseDouble(goalAmountString)).setPeriod(Integer.parseInt(goalPeriodString)).create();

                    push_task = mDbRef.child("Goals").push().setValue(goal);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                                if (push_task.isSuccessful()) {
                                      Toast.makeText(parentHolder.getContext(), "Goal Record Created Successfully", Toast.LENGTH_LONG).show();
                                }
                                else  {
                                        Toast.makeText(parentHolder.getContext(), "Error creating goal record", Toast.LENGTH_LONG).show();
                                    }

                        }
                    }, 1000);

                }

            }
        });

        return parentHolder;
    }
}
