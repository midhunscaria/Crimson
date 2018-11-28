package com.example.crimson.crimson;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class dash_fragment extends Fragment{

    public View parentHolder;

    public TextView nameLabel, ageLabel, occupationLabel, salaryLabel, subsLabel, typeLabel;

    public String nameStr, ageStr, occupationStr, salaryStr, subsStr, typeStr, silverStr, goldStr, diamondStr;

    public String user_id_fb;

    public String user_identifier = FirebaseAuth.getInstance().getUid();

    public DatabaseReference mDbRef = FirebaseDatabase.getInstance().getReference();
    public DatabaseReference userProfileRef = mDbRef.child("User_Details");

    public Handler handler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        parentHolder = inflater.inflate(R.layout.fragment_dash_fragment, container, false);

        nameLabel = (TextView)parentHolder.findViewById(R.id.dashName);
        ageLabel = (TextView)parentHolder.findViewById(R.id.dashAge);
        occupationLabel = (TextView)parentHolder.findViewById(R.id.dashOccupation);
        salaryLabel = (TextView)parentHolder.findViewById(R.id.dashSalary);
        subsLabel = (TextView)parentHolder.findViewById(R.id.dashSubs);
        typeLabel = (TextView)parentHolder.findViewById(R.id.dashSubsType);

        getUserProfileDetails(user_identifier);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                setProfile();

            }
        },2000);

        return parentHolder;
    }

    public void getUserProfileDetails(final String user_identifier)
    {
        userProfileRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    user_id_fb = ds.child("user_identifier").getValue(String.class);

                    if(user_id_fb.equals(user_identifier))
                    {
                        nameStr = ds.child("nameOfUser").getValue(String.class);
                        ageStr = ds.child("ageOfUser").getValue(String.class);
                        occupationStr = ds.child("occupationOfUser").getValue(String.class);
                        salaryStr = ds.child("annualIncomeofUser").getValue(String.class);
                        subsStr = ds.child("userType").getValue(String.class);

                        if(subsStr.equals("true"))
                        {
                            subsStr = "Subscribed Member";

                            silverStr = ds.child("userTypeSilver").getValue(String.class);
                            goldStr = ds.child("userTypeGold").getValue(String.class);
                            diamondStr = ds.child("userTypeDiamond").getValue(String.class);

                            if(silverStr != null && silverStr.equals("true"))
                                typeStr = "Silver";
                            else if(goldStr != null && goldStr.equals("true"))
                                typeStr = "Gold";
                            else if(diamondStr != null && diamondStr.equals("true"))
                                typeStr = "Diamond";
                        }
                        else
                        {
                            subsStr = "Free Memeber";
                            typeStr = "";
                        }

                        break;
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void setProfile()
    {
        nameLabel.setText(nameStr);
        ageLabel.setText(ageStr);
        occupationLabel.setText(occupationStr);
        salaryLabel.setText(salaryStr);
        subsLabel.setText(subsStr);
        typeLabel.setText(typeStr);
    }

}
