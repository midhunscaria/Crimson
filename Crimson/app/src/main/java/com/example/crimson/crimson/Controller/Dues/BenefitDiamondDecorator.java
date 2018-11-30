package com.example.crimson.crimson.Controller.Dues;

public class BenefitDiamondDecorator extends BenefitDecorator {

    public BenefitDiamondDecorator(BenefitBase base)
    {
        super(base);
    }

    @Override
    public String generateCoupon()
    {
        return "Amusement-2"+","+generateCouponAPI.generateCoupon();
    }
}
