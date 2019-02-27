package com.example.crimson.crimson.Controller.Interceptor;

import android.content.Context;

import com.example.crimson.crimson.Command.CallBackInterface;
import com.example.crimson.crimson.Command.GenerateReportService;
import com.example.crimson.crimson.Command.GenerateLoadBalanceService;
import com.example.crimson.crimson.Command.ServiceInvoker;
import com.example.crimson.crimson.Command.Services;
import com.example.crimson.crimson.Controller.Fragments.expense_fragment;
import com.example.crimson.crimson.Interfaces.Observer;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.io.IOException;

public class DataInterceptor implements Observer {

    public String u_id_str, amount_str, category_str, place_str;
    public expense_fragment exp_frag;

    public DataInterceptor(expense_fragment e_frag)
    {
        this.exp_frag = e_frag;

    }

    public void update() throws IOException {

        createContextObject();

//        ServiceInvoker invoker = new ServiceInvoker();
//        Services services = new Services();
//
//        CallBackInterface report = new GenerateReportService(services);
//        CallBackInterface loadBalanceService  = new GenerateLoadBalanceService(services);
//
//        invoker.setCallBackInterface(loadBalanceService);
//        invoker.invoke();



    }

    public void createContextObject()
    {
        this.u_id_str = this.exp_frag.user_id_fb;
        this.amount_str = this.exp_frag.amount_str;
        this.category_str = this.exp_frag.category;
        this.place_str = this.exp_frag.expense_place_str;

        ObjectOutputStream out = new ObjectOutputStream(file);

    }
}
