package com.example.crimson.crimson.Controller.BuilderClasses;

public class OneTimeStrategy implements Strategy{

    public Sample dOperation(DueSingleton h)
        {
            return ObjectFactory.getObject(h);
        }
}
