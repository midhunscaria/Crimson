package com.example.crimson.crimson.PluggableAdapter;

public class EquityInterestAdapter implements interestAdapterInterface {
    @Override
    public void adapt(double corpus, double timePeriod) {
        MutualFundCalc mfcalc = new MutualFundCalc();
        mfcalc.getInterestRate(corpus, timePeriod);
    }
}
