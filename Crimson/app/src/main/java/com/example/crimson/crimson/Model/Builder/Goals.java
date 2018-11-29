package com.example.crimson.crimson.Model.Builder;

public class Goals {

    private String goalTarget;
    private double goalAmount;
    private int goalPeriod;

    public String getGoalTarget() {
        return goalTarget;
    }

    public double getGoalAmount() {
        return goalAmount;
    }

    public int getGoalPeriod() {
        return goalPeriod;
    }

    public static class Builder {

        private String goalTarget;
        private double goalAmount;
        private int goalPeriod;

        public Builder setTarget(final String target){
            this.goalTarget=target;
            return this;
        }
        public  Builder setAmount(final double amount){
            this.goalAmount=amount;
            return this;
        }
        public Builder setPeriod(final int period){
            this.goalPeriod=period;
            return this;
        }
        public Goals create(){

            return new Goals(this);
        }

    }
    private Goals(Builder builder){
        goalAmount=builder.goalAmount;
        goalPeriod=builder.goalPeriod;
        goalTarget=builder.goalTarget;
    }
}
