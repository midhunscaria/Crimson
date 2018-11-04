package com.example.crimson.crimson;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class dues_fragment extends Fragment {

    public View parentHolder;
    public Spinner duesCategorySpinner;
    public EditText dueAmount;
    public  EditText dueReceiver;
    public EditText duePeriod;
    public EditText duesReceiverEmail;
    public Button dueSubmitButton;

    public FirebaseAuth mAuth;
    public DatabaseReference mDbRef = FirebaseDatabase.getInstance().getReference();

    public String user_identifier =  FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
    public Boolean task_successful;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        parentHolder = inflater.inflate(R.layout.fragment_dues_fragment, container, false);

        duesCategorySpinner = (Spinner)parentHolder.findViewById(R.id.dues_period_spinner);
        dueAmount = (EditText)parentHolder.findViewById(R.id.duesAmount);
        dueReceiver = (EditText)parentHolder.findViewById(R.id.duesReceiverName);
        duePeriod = (EditText)parentHolder.findViewById(R.id.duesPeriod);
        duesReceiverEmail = (EditText)parentHolder.findViewById(R.id.duesReceiverEmailID);
        dueSubmitButton = (Button)parentHolder.findViewById(R.id.createDueButton);


        dueSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                task_successful = false;

               if(TextUtils.isEmpty(dueReceiver.toString()) || TextUtils.isEmpty(dueAmount.toString()))
               {
                   Toast.makeText(parentHolder.getContext(), "Receiver Name and Amount are mandatory", Toast.LENGTH_LONG).show();
               }
               else
               {
                   if(TextUtils.equals(duesCategorySpinner.getSelectedItem().toString(), "Periodic") && TextUtils.isEmpty(duePeriod.toString()))
                   {
                       Toast.makeText(parentHolder.getContext(), "Period Value cannot be empty for a Periodic Due", Toast.LENGTH_LONG).show();
                   }
                   else
                   {
                        if(TextUtils.equals(duesCategorySpinner.getSelectedItem().toString(), "One Time"))
                        {
                            Dues due = new Dues.Builder().setName(dueReceiver.toString()).setAmount(Double.parseDouble(dueAmount.toString())).setEmailID(duesReceiverEmail.toString()).setUserIdentifier(user_identifier).create();

                            mDbRef.child("OneTime").push().setValue(due).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful())
                                    {
                                     task_successful = true;
                                    }
                                }
                            });
                        }
                        else if(TextUtils.equals(duesCategorySpinner.getSelectedItem().toString(), "Periodic")) {
                            Dues due = new Dues.Builder().setName(dueReceiver.toString()).setAmount(Double.parseDouble(dueAmount.toString())).setEmailID(duesReceiverEmail.toString()).setPeriod(Integer.parseInt(duePeriod.toString())).setUserIdentifier(user_identifier).create();

                            mDbRef.child("Periodic").push().setValue(due).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful())
                                    {
                                        task_successful = true;
                                    }
                                }
                            });
                        }
                   }

                   if(task_successful == true)
                   {
                       Toast.makeText(parentHolder.getContext(), "Due Record Created Successfully", Toast.LENGTH_LONG).show();
                   }
                   else if(task_successful == false)
                   {
                       Toast.makeText(parentHolder.getContext(), "Error creating due record", Toast.LENGTH_LONG).show();
                   }

               }
            }
        });

        return parentHolder;
    }

}
