package com.example.crimson.crimson.Command;

import java.io.IOException;

public class ServiceInvoker
{

    public CallBackInterface callBackInterface;

    public void setCallBackInterface(CallBackInterface callBackInterface)
    {
        this.callBackInterface = callBackInterface;
    }

    public void invoke() throws IOException {
        callBackInterface.execute();
    }

}
