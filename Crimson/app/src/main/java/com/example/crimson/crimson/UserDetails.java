package com.example.crimson.crimson;

public class UserDetails {

    private String name;
    private int age;
    private String occupation;
    private String user_identifier;
    private double annualIncome;
    private boolean userType;
    private boolean isSilver;
    private boolean isGolden;
    private boolean isDiamond;

    public String getNameOfUser(){ return name;}

    public int getAgeOfUser(){ return age; }

    public String getOccupationOfUser() { return occupation; }

    public double getAnnualIncomeofUser(){ return annualIncome; }

    public boolean getUserType(){ return userType; }

    public boolean getUserTypeSilver(){ return isSilver; }

    public boolean getUserTypeGolden(){ return isGolden; }

    public boolean getUserTypeDiamond() { return isDiamond; }

    public String getUser_identifier() {
        return user_identifier;
    }


    static class Builder {

        private String name;
        private int age;
        private String occupation;
        private String user_identifier;
        private double annualIncome;
        private boolean userType;
        private boolean isSilver;
        private boolean isGolden;
        private boolean isDiamond;

        public Builder setNameOfUser(final String name)
        {
            this.name = name;
            return this;
        }

        public Builder setAgeOfUser(final String age)
        {
            this.age = Integer.parseInt(age);
            return this;
        }

        public Builder setOccupationOfUser (final String occupation)
        {
            this.occupation = occupation;
            return this;
        }

        public Builder setAnnualIncomeOfUser(final String annualIncome)
        {
            this.annualIncome = Double.parseDouble(annualIncome);
            return this;
        }

        public Builder setUserTypeSilver(final boolean isSilver)
        {
            this.isSilver = isSilver;
            return this;
        }

        public Builder setUserTypeGolden(final boolean isGolden)
        {
            this.isGolden = isGolden;
            return this;
        }

        public Builder setUserTypeDiamond (final boolean isDiamond)
        {
            this.isDiamond = isDiamond;
            return this;
        }

        public Builder setUserType(final boolean userType)
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
        isSilver = builder.isSilver;
        isGolden = builder.isGolden;
        isDiamond = builder.isDiamond;
        user_identifier = builder.user_identifier;
    }

}
