package com.example.crimson.crimson.Controller.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.crimson.crimson.Controller.BuilderClasses.ObjectFactory;
import com.example.crimson.crimson.Controller.BuilderClasses.Sample;
import com.example.crimson.crimson.Controller.Dues.DueBridge;
import com.example.crimson.crimson.Controller.Dues.DueManager;
import com.example.crimson.crimson.Controller.Dues.DueOneTime;
import com.example.crimson.crimson.Controller.Dues.DuePeriodic;
import com.example.crimson.crimson.Interceptor.FilterManager;
import com.example.crimson.crimson.Interceptor.InputIntegrityFilter;
import com.example.crimson.crimson.Model.DAO;
import com.example.crimson.crimson.Controller.BuilderClasses.Dues;
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

    public FirebaseAuth mAuth;
    public DatabaseReference mDbRef;

    public String user_identifier =  FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
    public Boolean task_successful;

    public String duesCategorySpinnerString;
    public String dueAmountString;
    public String dueReceiverString;
    public String duePeriodString;
    public String dueReceiverEmailString;

    public Task<Void> db_push_task;
    public Sample due;
    public DueBridge generated_due_csv;
    public List<String> due_information_from_csv = new ArrayList<>();
    public Handler handler;
    public static boolean due_push_task_flag=false;
    public static Task<Void> due_push_task;

    public FilterManager filterManager;

    public void setFilterManager(FilterManager filterManager)
    {
        this.filterManager = filterManager;
    }

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

        filterManager = new FilterManager();
        filterManager.addFilter(new InputIntegrityFilter("Username Integrity Check"));
        this.setFilterManager(filterManager);

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

                if(TextUtils.isEmpty(dueReceiverString) || TextUtils.isEmpty(dueAmountString))
                {
                    Util.makeToast(parentHolder.getContext(), "Receiver Name and Amount are compulsory!").show();
                }
                else {

                    if (duesCategorySpinnerString.equals("One Time")) {

                        /**
                         * Bridge Pattern
                         *
                         * Used in this project in order to generate the different types of due objects
                         * as per due category.
                         *
                         *The DueManager takes the required parameters as per due category and constructs
                         * the required result string. The result string is generated using the generateResultString() method.
                         */
                        if (TextUtils.isEmpty(dueReceiverEmailString)) {
                            Util.makeToast(parentHolder.getContext(), "Receiver Email is compulsory for a One Time due!").show();
                        } else {


                            filterManager.execute(dueReceiverEmailString);

                            generated_due_csv = new DueManager(dueReceiverString, dueAmountString, new DueOneTime(dueReceiverEmailString));

                            due_information_from_csv = Arrays.asList(generated_due_csv.generateResultString().split(","));

                            due= ObjectFactory.getObject(due_information_from_csv.get(0),due_information_from_csv.get(1),"0",user_identifier,due_information_from_csv.get(2),"One Time");
                            //due = new Dues.Builder().setName(due_information_from_csv.get(0)).setAmount(due_information_from_csv.get(1)).setEmailID(due_information_from_csv.get(2)).setUserIdentifier(user_identifier).create();

                        }
                    }

                    else if (duesCategorySpinnerString.equals("Periodic")) {

                        if (TextUtils.isEmpty(duePeriodString)) {
                            Util.makeToast(parentHolder.getContext(), "Period is compulsory for a periodic due!").show();
                        } else {
                            generated_due_csv = new DueManager(dueReceiverString, dueAmountString, new DuePeriodic(duePeriodString));

                            due_information_from_csv = Arrays.asList(generated_due_csv.generateResultString().split(","));
                            due= ObjectFactory.getObject(due_information_from_csv.get(0),due_information_from_csv.get(1),due_information_from_csv.get(2),user_identifier,due_information_from_csv.get(2),"One Time");

                            //due = new Dues.Builder().setName(due_information_from_csv.get(0)).setAmount(due_information_from_csv.get(1)).setPeriod(due_information_from_csv.get(2)).setUserIdentifier(user_identifier).create();

                        }
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
            }
        });

        return parentHolder;
    }
}