package com.example.crimson.crimson;

public class BenefitCoupon
{
    private String coupon;
    private String userIdentifier;

    public String getCoupon() {
        return coupon;
    }

    public String getUserIdentifier() {
        return userIdentifier;
    }

    static class Builder
    {
        private String coupon;
        private String userIdentifier;

        public Builder setCoupon(String coupon)
        {
            this.coupon = coupon;
            return this;
        }

        public Builder setUserIdentifier(String userIdentifier)
        {
            this.userIdentifier = userIdentifier;
            return this;
        }

        public BenefitCoupon create()
        {
            return new BenefitCoupon(this);
        }
    }

    private BenefitCoupon(Builder builder)
    {
        this.coupon = builder.coupon;
        this.userIdentifier = builder.userIdentifier;
    }
}
