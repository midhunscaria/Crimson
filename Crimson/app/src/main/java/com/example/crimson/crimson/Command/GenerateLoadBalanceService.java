package com.example.crimson.crimson.Command;

import java.io.IOException;

public class GenerateLoadBalanceService implements CallBackInterface
{
    public Services service;

    public GenerateLoadBalanceService(Services service)
    {
        this.service = service;
    }

    @Override
    public void execute() throws IOException {
        service.generateLoadBalanceService();
    }
}
