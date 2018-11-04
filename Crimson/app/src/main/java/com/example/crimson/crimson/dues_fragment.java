package com.example.crimson.crimson;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.Toast;

import org.w3c.dom.Text;

public class dues_fragment extends Fragment {

    public View parentHolder;
    public Spinner duesCategorySpinner;
    public EditText dueAmount;
    public  EditText dueReceiver;
    public EditText duePeriod;
    public EditText duesReceiverEmail;
    public Button dueSubmitButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        parentHolder = inflater.inflate(R.layout.fragment_dues_fragment, container, false);

        duesCategorySpinner = (Spinner)parentHolder.findViewById(R.id.dues_period_spinner);
        dueAmount = (EditText)parentHolder.findViewById(R.id.duesAmountEditText);
        dueReceiver = (EditText)parentHolder.findViewById(R.id.duesReceiverEditText);
        duePeriod = (EditText)parentHolder.findViewById(R.id.duesPeriodEditText);
        duesReceiverEmail = (EditText)parentHolder.findViewById(R.id.duesReceiverEmailID);
        dueSubmitButton = (Button)parentHolder.findViewById(R.id.createDueButton);


        dueSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(dueAmount.toString()) || TextUtils.isEmpty(dueReceiver.toString()) || TextUtils.isEmpty(duePeriod.toString()))
                {
                    Toast.makeText(parentHolder.getContext(),"Please Enter all Fields!", Toast.LENGTH_LONG).show();
                }
                else {
                    if (TextUtils.equals(duesCategorySpinner.getSelectedItem().toString(), "One Time") && TextUtils.isEmpty(duesReceiverEmail.toString())) {
                        Toast.makeText(parentHolder.getContext(), "Receiver Email ID is mandatory for a One Time Due Entry", Toast.LENGTH_LONG).show();
                    } else if (TextUtils.equals(duesCategorySpinner.getSelectedItem().toString(), "Periodic") && (TextUtils.isEmpty(duePeriod.toString()))) {
                        Toast.makeText(parentHolder.getContext(), "Period is mandatory for a Periodic Due Entry", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        return parentHolder;
    }

}
