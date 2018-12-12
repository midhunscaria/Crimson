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

import com.example.crimson.crimson.Controller.Benefit.BenefitBase;
import com.example.crimson.crimson.Controller.BuilderClasses.BenefitCoupon;
import com.example.crimson.crimson.Controller.Benefit.BenefitDiamondDecorator;
import com.example.crimson.crimson.Controller.Benefit.BenefitGoldDecorator;
import com.example.crimson.crimson.Controller.Benefit.BenefitSilverDecorator;
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

import java.util.Arrays;
import java.util.List;


public class benefit_fragment extends Fragment{

    public View parentHolder;

    public TextView benefit_coupon_info;
    public TextView benefit_list1;
    public TextView benefit_list2;
    public Button benefit_redeem_button;

    public String checker;
    public boolean flag;

    public generateCouponAPI coupon;

    public Handler handler;
    public Task<Void> push_task;

    public DatabaseReference mDbRef = FirebaseDatabase.getInstance().getReference();
    public DatabaseReference benefitmDbRef = mDbRef.child("Benefits");

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
        benefit_list1=(TextView)parentHolder.findViewById(R.id.textView10);
        benefit_list2=(TextView)parentHolder.findViewById(R.id.textView12);

        benefit_redeem_button.setEnabled(false);

        checkIfRedeemed(user_identifier);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if(dash_fragment.typeStr == null) {
                    new AlertDialog.Builder(parentHolder.getContext()).setTitle("Sorry!").setMessage("Please register you profile first!").create().show();;
                }
                    else
                    {
                        if (flag) {
                            new AlertDialog.Builder(parentHolder.getContext()).setTitle("Sorry!").setMessage("Your benefits were already Redeemed!").create().show();
                        } else {
                            benefit_redeem_button.setEnabled(true);
                        }
                    }
            }
        }, 2000);


        benefit_redeem_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (dash_fragment.typeStr.equals("Silver")) {
                    coupon = new BenefitSilverDecorator(new BenefitBase());
                } else if (dash_fragment.typeStr.equals("Gold")) {
                    coupon = new BenefitGoldDecorator(new BenefitBase());

                } else if (dash_fragment.typeStr.equals("Diamond")) {
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

        benefitmDbRef.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    checker = ds.child("user_identifier").getValue(String.class);
                    couponStr = ds.child("coupon").getValue(String.class);

                    String []s= couponStr.split(",");
                    final List<String> coupon_list= Arrays.asList(s);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(checker.equals(user_identifier)) {
                                flag = true;
                                benefit_coupon_info.setText("You Redeemed ");
                                benefit_list1.setText(coupon_list.get(0).split("-")[0]+" Tickets");
                                benefit_list2.setText(coupon_list.get(1).split("-")[0]+" Tickets");

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
