package com.example.crimson.crimson;

public class UserDetails {

    private String name;
    private String age;
    private String occupation;
    private String user_identifier;
    private String annualIncome;
    private String userType;
    private String isSilver;
    private String isGolden;
    private String isDiamond;

    public String getNameOfUser(){ return name;}

    public String getAgeOfUser(){ return age; }

    public String getOccupationOfUser() { return occupation; }

    public String getAnnualIncomeofUser(){ return annualIncome; }

    public String getUserType(){ return userType; }

    public String getUserTypeSilver(){ return isSilver; }

    public String getUserTypeGolden(){ return isGolden; }

    public String getUserTypeDiamond() { return isDiamond; }

    public String getUser_identifier() {
        return user_identifier;
    }


    static class Builder {

        private String name;
        private String age;
        private String occupation;
        private String user_identifier;
        private String annualIncome;
        private String userType;
        private String isSilver;
        private String isGolden;
        private String isDiamond;

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

        public Builder setUserTypeSilver(final String isSilver)
        {
            this.isSilver = isSilver;
            return this;
        }

        public Builder setUserTypeGolden(final String isGolden)
        {
            this.isGolden = isGolden;
            return this;
        }

        public Builder setUserTypeDiamond (final String isDiamond)
        {
            this.isDiamond = isDiamond;
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
        isSilver = builder.isSilver;
        isGolden = builder.isGolden;
        isDiamond = builder.isDiamond;
        user_identifier = builder.user_identifier;
    }

}
