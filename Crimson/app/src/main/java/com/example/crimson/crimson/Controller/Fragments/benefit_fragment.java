package com.example.crimson.crimson.Controller.Fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import android.os.Handler;
import android.widget.Toast;

import com.example.crimson.crimson.Controller.Dues.BenefitBase;
import com.example.crimson.crimson.Controller.Dues.BenefitCoupon;
import com.example.crimson.crimson.Controller.Dues.BenefitDiamondDecorator;
import com.example.crimson.crimson.Controller.Dues.BenefitGoldDecorator;
import com.example.crimson.crimson.Controller.Dues.BenefitSilverDecorator;
import com.example.crimson.crimson.Interfaces.generateCouponAPI;
import com.example.crimson.crimson.R;
import com.example.crimson.crimson.Utility.Util;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class benefit_fragment extends Fragment{

    public View parentHolder;

    public TextView benefit_coupon_info;
    public Button benefit_redeem_button;

    public String benefit_subs_type_Str, checker;
    public boolean flag;

    public generateCouponAPI coupon;

    public Handler handler;
    public Task<Void> push_task;

    public DatabaseReference mDbRef = FirebaseDatabase.getInstance().getReference();
    public DatabaseReference benefitmDbRef = mDbRef.child("Benefits");
    public DatabaseReference userProfileRef = mDbRef.child("User_Details");

    public String user_identifier = FirebaseAuth.getInstance().getUid();
    public String couponStr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        parentHolder = inflater.inflate(R.layout.fragment_benefit_fragment, container, false);

        handler = new Handler();

        benefit_coupon_info = (TextView)parentHolder.findViewById(R.id.benefits_ticket_info_id);
        benefit_redeem_button = (Button)parentHolder.findViewById(R.id.benefits_redeem_button);

        benefit_redeem_button.setEnabled(false);

        checkIfRedeemed(user_identifier);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(parentHolder.getContext(), ""+benefit_subs_type_Str, Toast.LENGTH_LONG).show();
            }
        }, 2000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(flag) {
                    new AlertDialog.Builder(parentHolder.getContext()).setTitle("Sorry!").setMessage("Your benefits were already Redeemed!").create().show();
                }
                else {
                    benefit_redeem_button.setEnabled(true);
                }

            }
        }, 2000);

        benefit_redeem_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (benefit_subs_type_Str.equals("Silver")) {
                    coupon = new BenefitSilverDecorator(new BenefitBase());
                } else if (benefit_subs_type_Str.equals("Gold")) {
                    coupon = new BenefitGoldDecorator(new BenefitBase());

                } else if (benefit_subs_type_Str.equals("Diamond")) {
                    coupon = new BenefitDiamondDecorator(new BenefitBase());
                } else {
                    Util.makeToast(parentHolder.getContext(), "This feature is for Subscription Members Only!").show();
                }

                handler = new Handler();

                BenefitCoupon benefit = new BenefitCoupon.Builder().setCoupon(coupon.generateCoupon().toString()).setUserIdentifier(user_identifier).create();

                push_task = mDbRef.child("Benefits").push().setValue(benefit);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (push_task.isSuccessful()) {
                            Util.makeToast(parentHolder.getContext(), "Redeemed!").show();
                            benefit_redeem_button.setEnabled(false);

                        } else {
                            Util.makeToast(parentHolder.getContext(), "Error in redeeming benefits!").show();
                        }

                    }
                }, 1000);
            }
        });

        return parentHolder;
    }

    public void checkIfRedeemed(final String user_identifier)
    {
        flag = false;

        benefitmDbRef.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    checker = ds.child("user_identifier").getValue(String.class);
                    couponStr = ds.child("coupon").getValue(String.class);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(checker.equals(user_identifier)) {
                                flag = true;
                                benefit_coupon_info.setText("You Got "+couponStr);
                            }

                        }
                    }, 200);

                    if(flag)
                    {
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                    throw new UnsupportedOperationException();
            }
        });

    }

}
