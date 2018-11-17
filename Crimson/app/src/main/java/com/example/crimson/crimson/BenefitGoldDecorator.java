package com.example.crimson.crimson;

public class BenefitGoldDecorator extends BenefitDecorator {

    public BenefitGoldDecorator(BenefitBase base)
    {
        super(base);
    }

    public String generateCoupon()
    {
        return "Dinner-2"+","+generateCouponAPI.generateCoupon();
    }
}
