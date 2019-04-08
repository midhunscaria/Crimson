package com.example.crimson.crimson.PluggableAdapter;

public class EquityCalc implements interestCalc {
    @Override
    public double getInterestRate(double corpus, double timePeriod) {
        return (corpus + (corpus * timePeriod)/3);
    }
}
