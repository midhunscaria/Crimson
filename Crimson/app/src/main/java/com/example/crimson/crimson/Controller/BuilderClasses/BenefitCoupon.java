package com.example.crimson.crimson.Controller.BuilderClasses;

public class BenefitCoupon
{
    private String coupon;
    private String user_identifier;
    public String getCoupon() {
        return coupon;
    }

    public String getUser_identifier() {
        return user_identifier;
    }

    public static class Builder
    {


        private String coupon;
        private String user_identifier;

        public Builder setCoupon(String coupon)
        {
            this.coupon = coupon;
            return this;
        }

        public Builder setUserIdentifier(String userIdentifier)
        {
            this.user_identifier = userIdentifier;
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
        this.user_identifier = builder.user_identifier;
    }
}
