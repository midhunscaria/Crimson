package com.example.crimson.crimson.PluggableAdapter;

public class MutualFundsInterestAdapter implements compoundInterestAdapterInterface {

    @Override
    public double calculateCompoundInterest(double corpus, double timePeriod, double roi) {
        return (50* (corpus * timePeriod * roi)) / 100;
    }
}
