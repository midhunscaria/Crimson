package com.example.crimson.crimson.NullChecker;

public class NodeDoesNotExist implements ICheckNull {

    @Override
    public boolean isNull() {
        return false;
    }
}
