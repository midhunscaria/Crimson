package com.example.crimson.crimson.Model;

public class Dues {

    private String name;
    private String email_id;
    private String amount;
    private String period;

    public String getName() {
        return name;
    }

    public String getEmail_id() {
        return email_id;
    }

    public String getAmount() {
        return amount;
    }

    public String getPeriod() {
        return period;
    }

    public String getUser_identifier() {
        return user_identifier;
    }

    private String user_identifier;

    public static class Builder
    {
        private String name;
        private String email_id;
        private String amount;
        private String period;
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

        public Builder setAmount(final String amount)
        {
            this.amount = amount;
            return this;
        }

        public Builder setPeriod(final String period)
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
