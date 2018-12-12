package com.example.crimson.crimson.Controller.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.os.Handler;

import com.example.crimson.crimson.Model.DAO;
import com.example.crimson.crimson.Controller.BuilderClasses.Goals;
import com.example.crimson.crimson.R;
import com.example.crimson.crimson.Utility.Util;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


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
    public String user_identifier = FirebaseAuth.getInstance().getUid();
    public String goalTargetString;
    public String goalAmountString;
    public String goalPeriodString;
    public static boolean push_task;

    public Handler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        handler = new Handler();

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
                    Util.makeToast(parentHolder.getContext(), "Enter All Details Correctly!").show();
                } else {
                    goal = new Goals.Builder().setTarget(goalTargetString).setAmount(goalAmountString).setPeriod(goalPeriodString).setUserIdentifier(user_identifier).create();

                    DAO.pushGoals(goal,mDbRef);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (push_task) {
                                Util.makeToast(parentHolder.getContext(), "Goal Record Created Successfully").show();
                            }
                            else  {
                                Util.makeToast(parentHolder.getContext(), "Error creating goal record").show();
                            }

                        }
                    }, 2000);

                }

            }
        });

        return parentHolder;
    }
}