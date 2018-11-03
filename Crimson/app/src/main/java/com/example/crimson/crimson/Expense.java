package com.example.crimson.crimson;

public class Expense {

    private double amount;
    private String category;
    private String place;
    private String user_identifier;

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getPlace() {
        return place;
    }

    public String getUser_identifier() {
        return user_identifier;
    }

    static class Builder
    {
        private double amount;
        private String category;
        private String place;
        private String user_identifier;

        public Builder setAmount(final double amount)
        {
            this.amount = amount;
            return this;
        }

        public Builder setCategory(final String category)
        {
            this.category = category;
            return this;
        }

        public Builder setPlace(final String place)
        {
            this.place = place;
            return this;
        }

        public Builder setUserIdentifier(final String user_identifier)
        {
            this.user_identifier = user_identifier;
            return this;
        }

        public Expense create()
        {
            return new Expense(this);
        }
    }

    private Expense(Builder builder)
    {
        amount = builder.amount;
        category = builder.category;
        place = builder.place;
        user_identifier = builder.user_identifier;
    }
}
