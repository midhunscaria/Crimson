package com.example.crimson.crimson;

public class BenefitDiamondDecorator extends BenefitDecorator {

    public BenefitDiamondDecorator(BenefitBase base)
    {
        super(base);
    }

    public String generateCoupon()
    {
        return "Amusement-2"+","+generateCouponAPI.generateCoupon();
    }
}
