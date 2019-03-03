package com.example.crimson.crimson.Controller.BuilderClasses;

public class StrategyContext {
    private Strategy strategy;

    public StrategyContext(Strategy strategy){
        this.strategy = strategy;
    }

    public Sample executeStrategy(DueSingleton s){
        return strategy.dOperation(s);
    }
}

