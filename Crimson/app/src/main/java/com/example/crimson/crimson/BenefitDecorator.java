package com.example.crimson.crimson;

public abstract class BenefitDecorator implements generateCouponAPI {

    protected generateCouponAPI generateCouponAPI;

    public BenefitDecorator(generateCouponAPI generateCouponAPI)
    {
        this.generateCouponAPI = generateCouponAPI;
    }

    public String generateCoupon()
    {
        return generateCouponAPI.generateCoupon();
    }

}
