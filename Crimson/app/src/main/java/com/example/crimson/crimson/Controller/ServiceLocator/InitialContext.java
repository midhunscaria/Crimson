package com.example.crimson.crimson.Controller.ServiceLocator;

public class InitialContext {

    public Object lookup(String serviceName){

        if(serviceName.equals("notifyDueService")){
            return new notifyDueService();
        }
        else if(serviceName.equals("DBUpdateService")){
            return new DBUpdateService();
        }
        return null;
    }
}
