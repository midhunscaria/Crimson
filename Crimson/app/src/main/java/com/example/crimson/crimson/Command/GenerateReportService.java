package com.example.crimson.crimson.Command;

public class GenerateReportService implements CallBackInterface
{
    Services service;

    public GenerateReportService(Services service)
    {
        this.service = service;
    }

    @Override
    public void execute()
    {
        service.generateReport();
    }
}
