package com.example.crimson.crimson.Command;

public class RemoteControl
{
    public Command command;

    public void setCommand(Command command)
    {
        this.command = command;
    }

    public void buttonPressed()
    {
        command.execute();
    }
}
