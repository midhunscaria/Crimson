package com.example.crimson.crimson;

public class Goals {

    private String goalTarget;
    private double goalAmount;
    private int goalperiod;

    static class Builder {

        private String goalTarget;
        private double goalAmount;
        private int goalperiod;
        public Builder setTarget(final String target){
            this.goalTarget=target;
            return this;
        }
        public  Builder setAmount(final double amount){
            this.goalAmount=amount;
            return this;
        }
        public Builder setPeriod(final int period){
            this.goalperiod=period;
            return this;
        }
        public Goals create(){

            return new Goals(this);
        }

    }
    private Goals(Builder builder){
        goalAmount=builder.goalAmount;
        goalperiod=builder.goalperiod;
        goalTarget=builder.goalTarget;
    }
}
