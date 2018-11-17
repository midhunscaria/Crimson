package com.example.crimson.crimson;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

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
    public Dues due;
    public DueBridge generated_due_csv;
    public List<String> due_information_from_csv = new ArrayList<>();
    public Handler handler;

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

                if(TextUtils.isEmpty(dueReceiverString) || TextUtils.isEmpty(dueAmountString))
                {
                    Toast.makeText(parentHolder.getContext(), "Receiver Name and Amount are compulsory!", Toast.LENGTH_LONG).show();
                }
                else {

                    if (duesCategorySpinnerString.equals("One Time")) {
                        generated_due_csv = new DueManager(dueReceiverString, dueAmountString, new DueOneTime(dueReceiverEmailString));

                        due_information_from_csv = Arrays.asList(generated_due_csv.generateResultString().toString().split(","));

                        due = new Dues.Builder().setName(due_information_from_csv.get(0)).setAmount(Double.parseDouble(due_information_from_csv.get(1))).setEmailID(due_information_from_csv.get(2)).setUserIdentifier(user_identifier).create();

                        db_push_task = mDbRef.child("Dues").child("OneTime").push().setValue(due);

                        task_successful = true;
                    }

                    else if (duesCategorySpinnerString.equals("Periodic")) {

                        if (TextUtils.isEmpty(duePeriodString)) {
                            Toast.makeText(parentHolder.getContext(), "Period is compulsory for a periodic due!", Toast.LENGTH_LONG).show();
                        } else {
                            generated_due_csv = new DueManager(dueReceiverString, dueAmountString, new DuePeriodic(duePeriodString));

                            due_information_from_csv = Arrays.asList(generated_due_csv.generateResultString().toString().split(","));

                            due = new Dues.Builder().setName(due_information_from_csv.get(0)).setAmount(Double.parseDouble(due_information_from_csv.get(1))).setPeriod(Integer.parseInt(due_information_from_csv.get(2))).setUserIdentifier(user_identifier).create();

                            db_push_task = mDbRef.child("Dues").child("Periodic").push().setValue(due);

                            task_successful = true;
                        }
                    }

                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {

                            if(task_successful) {
                                Toast.makeText(parentHolder.getContext(), "Due Record created Successfully!", Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(parentHolder.getContext(), "Error in Due Creation", Toast.LENGTH_LONG).show();
                            }
                        }
                    }, 2000);
                }
            }
        });

        return parentHolder;
    }

}
