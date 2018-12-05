package com.example.crimson.crimson.Controller.Benefit;

import com.example.crimson.crimson.Interfaces.generateCouponAPI;

public class BenefitBase implements generateCouponAPI {

    //Base Benefit. All decorators decorate over this base.
    public String generateCoupon()
    {
        return "Discount-2"+",";
    }

}
