package com.example.crimson.crimson.Controller.BuilderClasses;

import android.util.Log;

public class OneTimeStrategy implements Strategy{


public Sample dOperation(DueSingleton h){


     return ObjectFactory.getObject(h);

        //object factory
//        return newDueOneTime;
    }

}
