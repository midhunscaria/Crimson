package com.example.crimson.crimson;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class dash_fragment extends Fragment implements Observer{

    public View parentHolder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        parentHolder = inflater.inflate(R.layout.fragment_dash_fragment, container, false);
        return parentHolder;
    }

    @Override
    public void update(String status, String receiver_email) {
        if(receiver_email != null)
        {
            //send email to receiver email
            //add amount as new record in db
        }
        else
        {
            new AlertDialog.Builder(parentHolder.getContext()).setTitle("Reminder").setMessage("This Email ID is not registered with Crimson. An Email Notification will not be sent to this Email ID").create().show();
        }
    }
}
