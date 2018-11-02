package com.example.crimson.crimson;

public class Expense {

    private double amount;
    private String category;
    private String place;

    static class Builder
    {
        private double amount;
        private String category;
        private String place;

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
    }
}
