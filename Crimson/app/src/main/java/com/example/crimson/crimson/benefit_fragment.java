package com.example.crimson.crimson;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class benefit_fragment extends Fragment {

    public View parentHolder;

    public TextView benefit_username, benefit_subs, benefit_subs_type, benefit_coupon_info;
    public Button benefit_redeem_button;

    public String benefit_subs_type_Str;

    public generateCouponAPI coupon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        parentHolder = inflater.inflate(R.layout.fragment_benefit_fragment, container, false);

        benefit_username = (TextView)parentHolder.findViewById(R.id.benefit_username_id);
        benefit_subs = (TextView)parentHolder.findViewById(R.id.benefit_subs_id);
        benefit_subs_type = (TextView)parentHolder.findViewById(R.id.benefit_subs_type_id);
        benefit_coupon_info = (TextView)parentHolder.findViewById(R.id.benefits_ticket_info_id);

        benefit_subs_type_Str = benefit_subs_type.getText().toString();
        benefit_subs_type_Str="Gold";

        benefit_redeem_button = (Button)parentHolder.findViewById(R.id.benefits_redeem_button);

        benefit_redeem_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(benefit_subs_type_Str.equals("Silver"))
                {
                    coupon = new BenefitSilverDecorator(new BenefitBase());
                }
                else if(benefit_subs_type_Str.equals("Gold"))
                {
                    coupon = new BenefitGoldDecorator(new BenefitBase());

                }
                else if(benefit_subs_type_Str.equals("Diamond"))
                {
                    coupon = new BenefitDiamondDecorator(new BenefitBase());
                }
                else
                {
                    Toast.makeText(parentHolder.getContext(), "This feature is for Subscription Members Only!", Toast.LENGTH_LONG).show();
                }
                Toast.makeText(parentHolder.getContext(),coupon.generateCoupon(), Toast.LENGTH_LONG).show();

            }
        });

        return parentHolder;
    }


}
