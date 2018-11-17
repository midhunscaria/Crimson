package com.example.crimson.crimson;

public class BenefitSilverDecorator extends BenefitDecorator {

    public BenefitSilverDecorator(BenefitBase base)
    {
        super(base);
    }

    public String generateCoupon()
    {
        return "Movie-2"+","+generateCouponAPI.generateCoupon();
    }
}
