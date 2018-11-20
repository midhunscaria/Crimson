package com.example.crimson.crimson;

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

    static class Builder
    {
        public String coupon;
        public String user_identifier;

        public Builder setCoupon(String coupon)
        {
            this.coupon = coupon;
            return this;
        }

        public Builder setUserIdentifier(String user_identifier)
        {
            this.user_identifier = user_identifier;
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
