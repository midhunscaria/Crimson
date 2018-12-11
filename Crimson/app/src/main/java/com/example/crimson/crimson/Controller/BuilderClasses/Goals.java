package com.example.crimson.crimson.Controller.BuilderClasses;

public class Goals {

    private String goalTarget;
    private String goalAmount;
    private String goalPeriod;
    private String user_identifier;

    public String getGoalTarget() {
        return goalTarget;
    }

    public String getGoalAmount() {
        return goalAmount;
    }

    public String getGoalPeriod() {
        return goalPeriod;
    }

    public String getUserIdentifier() {return user_identifier; }

    public static class Builder {

        private String goalTarget;
        private String goalAmount;
        private String goalPeriod;
        private String user_identifier;

        public Builder setTarget(final String target){
            this.goalTarget=target;
            return this;
        }
        public  Builder setAmount(final String amount){
            this.goalAmount=amount;
            return this;
        }
        public Builder setPeriod(final String period){
            this.goalPeriod=period;
            return this;
        }
        public Builder setUserIdentifier(final String user_identifier)
        {
            this.user_identifier = user_identifier;
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
        user_identifier=builder.user_identifier;
    }
}
