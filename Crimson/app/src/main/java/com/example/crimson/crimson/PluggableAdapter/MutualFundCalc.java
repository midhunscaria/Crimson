package com.example.crimson.crimson.PluggableAdapter;

public class MutualFundCalc implements interestCalc {
    @Override
    public double getInterestRate(double corpus, double timePeriod) {
        return (corpus + (corpus * timePeriod)/7);
    }
}
