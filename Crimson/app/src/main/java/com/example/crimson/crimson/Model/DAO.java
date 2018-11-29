package com.example.crimson.crimson.Model;

import android.os.Handler;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class DAO
{
    public static DatabaseReference mDbRef = FirebaseDatabase.getInstance().getReference();
    public static DatabaseReference userProfileRef = mDbRef.child("User_Details");

    public static String nameStr, ageStr, occupationStr, salaryStr, subsStr, silverStr, goldStr, diamondStr, typeStr;

    public static void getUserProfileDetails(final String user_identifier)
    {
        mDbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    String user_id_fb = ds.child("user_identifier").getValue(String.class);

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
                                typeStr = "Silver Subscription";
                            else if(goldStr != null && goldStr.equals("true"))
                                typeStr = "Gold Subscription";
                            else if(diamondStr != null && diamondStr.equals("true"))
                                typeStr = "Diamond Subscription";
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
}
