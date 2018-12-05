package com.example.crimson.crimson.Controller.BuilderClasses;

public class UserDetails {

    private String name;
    private String age;
    private String occupation;
    private String user_identifier;
    private String annualIncome;
    private String userType;
    private String subsType;

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getUser_identifier() {
        return user_identifier;
    }

    public String getAnnualIncome() {
        return annualIncome;
    }

    public String getUserType() {
        return userType;
    }

    public String getSubsType() {
        return subsType;
    }


    public static class Builder {

        private String name;
        private String age;
        private String occupation;
        private String user_identifier;
        private String annualIncome;
        private String userType;
        private String subsType;

        public Builder setNameOfUser(final String name)
        {
            this.name = name;
            return this;
        }

        public Builder setAgeOfUser(final String age)
        {
            this.age = age;
            return this;
        }

        public Builder setOccupationOfUser (final String occupation)
        {
            this.occupation = occupation;
            return this;
        }

        public Builder setAnnualIncomeOfUser(final String annualIncome)
        {
            this.annualIncome = annualIncome;
            return this;
        }

        public Builder setUserSubsType(final String subsType)
        {
            this.subsType = subsType;
            return this;
        }

        public Builder setUserType(final String userType)
        {
            this.userType = userType;
            return this;
        }

        public Builder setUserIdentifier(final String userIdentifier)
        {
            this.user_identifier = userIdentifier;
            return this;
        }

        public UserDetails create()
        {
            return new UserDetails(this);
        }

    }

    private UserDetails(Builder builder){

        name = builder.name;
        age = builder.age;
        occupation = builder.occupation;
        annualIncome = builder.annualIncome;
        userType = builder.userType;
        subsType = builder.subsType;
        user_identifier = builder.user_identifier;
    }

}
