package com.example.crimson.crimson.Controller.Interceptor;

import android.util.Log;

public class ContextObject implements ContextObjectInterface
{

    public String url_wo_port = "http://10.102.24.215";
    public int defaultPort = 8080;

    public String method = "GET";

    public int load_balanced_port;

    public int sendRequestToPort = 8080;

    @Override
    public String getHost() {
        return this.url_wo_port;
    }

    @Override
    public void setHost(String host) {
        this.url_wo_port = host;
    }

    @Override
    public int getPort() {
        return this.sendRequestToPort;
    }

    @Override
    public void setPort(int port) {

        if(port != this.defaultPort) {
            Log.i("LB: Port Changed From: ", "" + sendRequestToPort);
            setLoadBalancedPort(port);
            Log.i("LB: Port Changed To: ", "" + load_balanced_port);
        }
    }

    @Override
    public int getLoadBalancedPort() {
        return this.load_balanced_port;
    }

    @Override
    public void setLoadBalancedPort(int port) {
        this.load_balanced_port = port;
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public void setMethod(String method) {
        this.method = method;
    }

}
