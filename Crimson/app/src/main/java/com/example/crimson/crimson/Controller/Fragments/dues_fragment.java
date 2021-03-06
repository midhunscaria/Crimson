package com.example.crimson.crimson.Controller.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;

import com.example.crimson.crimson.Controller.BuilderClasses.DueSingleton;
import com.example.crimson.crimson.Controller.BuilderClasses.OneTimeStrategy;
import com.example.crimson.crimson.Controller.BuilderClasses.PeriodicStrategy;
import com.example.crimson.crimson.Controller.BuilderClasses.Sample;
import com.example.crimson.crimson.Controller.BuilderClasses.StrategyContext;
import com.example.crimson.crimson.Model.DAO;
import com.example.crimson.crimson.PluggableAdapter.PluggableAdapter;
import com.example.crimson.crimson.R;
import com.example.crimson.crimson.Utility.Util;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class dues_fragment extends Fragment
{
    public View parentHolder;
    public Spinner duesCategorySpinner;
    public EditText dueAmount;
    public EditText dueReceiver;
    public EditText duePeriod;
    public EditText duesReceiverEmail;
    public Button dueSubmitButton;

    public DatabaseReference mDbRef;

    public String user_identifier =  FirebaseAuth.getInstance().getCurrentUser().getUid();
    public Boolean task_successful;

    public String duesCategorySpinnerString;
    public String dueAmountString;
    public String dueReceiverString;
    public String duePeriodString;
    public String dueReceiverEmailString;

    public Sample due;
    public Handler handler;
    public static boolean due_push_task_flag=false;
    public static Task<Void> due_push_task;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment

        parentHolder = inflater.inflate(R.layout.fragment_dues_fragment, container, false);

        handler = new Handler();

        mDbRef = FirebaseDatabase.getInstance().getReference();

        duesCategorySpinner = (Spinner)parentHolder.findViewById(R.id.dues_period_spinner);
        dueAmount = (EditText)parentHolder.findViewById(R.id.duesAmount);
        dueReceiver = (EditText)parentHolder.findViewById(R.id.duesReceiverName);
        duePeriod = (EditText)parentHolder.findViewById(R.id.duesPeriod);
        duesReceiverEmail = (EditText)parentHolder.findViewById(R.id.duesReceiverEmailID);
        dueSubmitButton = (Button)parentHolder.findViewById(R.id.createDueButton);

        dueSubmitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                duesCategorySpinnerString = duesCategorySpinner.getSelectedItem().toString();
                dueAmountString = dueAmount.getText().toString();
                dueReceiverString = dueReceiver.getText().toString();
                duePeriodString = duePeriod.getText().toString();
                dueReceiverEmailString = duesReceiverEmail.getText().toString();

                task_successful = false;


                DueSingleton dueSingleton=new DueSingleton(dueReceiverString,dueAmountString,duePeriodString,duesCategorySpinnerString,dueReceiverEmailString,user_identifier);


                    if (duesCategorySpinnerString.equals("One Time")) {

                        StrategyContext sc = new StrategyContext(new OneTimeStrategy());

                        DAO.pushDues(sc.executeStrategy(dueSingleton), mDbRef, duesCategorySpinnerString);


                    }

                    else if (duesCategorySpinnerString.equals("Periodic")) {

                        StrategyContext sc = new StrategyContext(new PeriodicStrategy());

                        DAO.pushDues(sc.executeStrategy(dueSingleton), mDbRef, duesCategorySpinnerString);

                    }

                    DAO.pushDues(due,mDbRef,duesCategorySpinnerString);

                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {

                            if(due_push_task_flag) {
                                Util.makeToast(parentHolder.getContext(), "Due Record created Successfully!").show();
                            }
                            else
                            {
                                Util.makeToast(parentHolder.getContext(), "Error in Due Creation").show();
                            }
                        }
                    }, 2000);
            }
        });

        return parentHolder;
    }
}