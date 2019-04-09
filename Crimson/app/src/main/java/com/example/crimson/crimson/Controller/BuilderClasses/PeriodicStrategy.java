package com.example.crimson.crimson.Controller.BuilderClasses;

import android.util.Log;

public class PeriodicStrategy implements Strategy {

    @Override
    public Sample dOperation(DueSingleton h) {

        return ObjectFactory.getObject(h);
    }
}
