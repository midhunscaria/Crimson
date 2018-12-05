package com.example.crimson.crimson.Controller.Benefit;

import com.example.crimson.crimson.Interfaces.generateCouponAPI;

public abstract class BenefitDecorator implements com.example.crimson.crimson.Interfaces.generateCouponAPI {

    protected generateCouponAPI generateCouponAPI;

    //Returns Discount Coupons as basic benefits and then decorates over base as per subscriber type
    public BenefitDecorator(generateCouponAPI generateCouponAPI)
    {
        this.generateCouponAPI = generateCouponAPI;
    }

    public String generateCoupon()
    {
        return generateCouponAPI.generateCoupon();
    }

}
