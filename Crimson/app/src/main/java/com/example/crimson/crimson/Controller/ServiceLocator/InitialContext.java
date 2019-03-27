package com.example.crimson.crimson.Controller.ServiceLocator;

public class InitialContext {

    public Object lookup(String serviceName){

        if(serviceName.equalsIgnoreCase("GoalsUpdateService")){
            return new GoalsUpdateService();
        }
        else if (serviceName.equalsIgnoreCase("UserProfileUpdateService")){
            return new UserProfileUpdateService();
        }
        if(serviceName.equals("notifyDueService")){
            return new notifyDueService();
        }
        return null;
    }
}