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

import com.google.android.gms.tasks.OnCompleteListener;
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
    public Dues due;
    public Handler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment

        parentHolder = inflater.inflate(R.layout.fragment_dues_fragment, container, false);

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

//                due = new Dues.Builder().setName(dueReceiverString).setAmount(Double.parseDouble(dueAmountString)).setEmailID(dueReceiverEmailString).setUserIdentifier(user_identifier).create();
//                db_push_task = mDbRef.child("Dues").child("OneTime").push().setValue(due);
//
//                Toast.makeText(parentHolder.getContext(),"Done",Toast.LENGTH_LONG).show();

//                handler = new Handler();
//
//                task_successful = false;

//               if(TextUtils.isEmpty(dueReceiverString)|| TextUtils.isEmpty(dueAmountString)|| !TextUtils.isDigitsOnly(dueAmountString))
//               {
//                   Toast.makeText(parentHolder.getContext(), "Receiver Name and Amount are mandatory", Toast.LENGTH_LONG).show();
//               }
//               else
//               {
//                   if(TextUtils.equals(duesCategorySpinnerString, "Periodic") && (TextUtils.isEmpty(duePeriodString)&& !TextUtils.isDigitsOnly(duePeriodString)))
//                   {
//                       Toast.makeText(parentHolder.getContext(), "Period Value cannot be empty for a Periodic Due", Toast.LENGTH_LONG).show();
//                   }
//                   else
//                   {
//                        if(TextUtils.equals(duesCategorySpinnerString, "One Time"))
//                        {
//                            due = new Dues.Builder().setName(dueReceiverString).setAmount(Double.parseDouble(dueAmountString)).setEmailID(dueReceiverEmailString).setUserIdentifier(user_identifier).create();
//
//                            db_push_task = mDbRef.child("OneTime").push().setValue(due);
//
//                            handler.postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//                                    db_push_task.addOnCompleteListener(new OnCompleteListener<Void>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<Void> task) {
//
//                                            if (task.isSuccessful()) {
//                                                task_successful = true;
//
//
//                                            }
//                                        }
//                                    });
//                                }
//                            }, 500);
//
//
//                        }
//                        else if(TextUtils.equals(duesCategorySpinnerString, "Periodic")) {
//                            due = new Dues.Builder().setName(dueReceiverString).setAmount(Double.parseDouble(dueAmountString)).setEmailID(dueReceiverEmailString).setPeriod(Integer.parseInt(duePeriodString)).setUserIdentifier(user_identifier).create();
//
//                            handler.postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//                                    mDbRef.child("Periodic").push().setValue(due).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<Void> task) {
//
//                                            if(task.isSuccessful())
//                                            {
//                                                task_successful = true;
//                                            }
//                                        }
//                                    });
//                                }
//                            }, 500);
//                        }
//                   }
//
//                   if(task_successful == true)
//                   {
//                       Toast.makeText(parentHolder.getContext(), "Due Record Created Successfully", Toast.LENGTH_LONG).show();
//                   }
//                   else if(task_successful == false)
//                   {
//                       Toast.makeText(parentHolder.getContext(), "Error creating due record", Toast.LENGTH_LONG).show();
//                   }
//
//               }
            }
        });

        return parentHolder;
    }

}
