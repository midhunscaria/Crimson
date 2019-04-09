package com.example.crimson.crimson.Controller.ServiceLocator;

public class ServiceLocator {

    private static Cache cache;

    static {
        cache = new Cache();
    }

    public static Service getService(String serviceName){

        Service service = cache.getService(serviceName);

        if(service != null){
            return service;
        }

        InitialContext context = new InitialContext();
        Service service_obj = (Service)context.lookup(serviceName);
        cache.addService(service_obj);
        return service_obj;
    }
}
