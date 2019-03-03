package com.example.crimson.crimson.Controller.BuilderClasses;

public class ObjectFactory {
    public static Sample getObject(DueSingleton dueSingleton)
    {

        if ("One Time".equalsIgnoreCase(dueSingleton.singleCategory)) return new NewDueOneTime(dueSingleton);
        else if("Periodic".equalsIgnoreCase(dueSingleton.singleCategory))return new NewPeriodicDue(dueSingleton);
        return null;
    }
}

