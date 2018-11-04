package com.example.crimson.crimson;

public class Dues {

    private String name;
    private String email_id;
    private double amount;
    private int period;
    private String user_identifier;

    static class Builder
    {
        private String name;
        private String email_id;
        private double amount;
        private int period;
        private String user_identifier;

        public Builder setName(final String name)
        {
            this.name = name;
            return this;
        }

        public Builder setEmailID(final String email_id)
        {
            this.email_id = email_id;
            return this;
        }

        public Builder setAmount(final double amount)
        {
            this.amount = amount;
            return this;
        }

        public Builder setPeriod(final int period)
        {
            this.period = period;
            return this;
        }

        public Builder setUserIdentifier(final String user_identifier)
        {
            this.user_identifier = user_identifier;
            return this;
        }

        public Dues create()
        {
            return new Dues(this);
        }
    }

    private Dues(Builder builder)
    {
        name = builder.name;
        email_id = builder.email_id;
        amount = builder.amount;
        period = builder.period;
        user_identifier = builder.user_identifier;
    }
}
