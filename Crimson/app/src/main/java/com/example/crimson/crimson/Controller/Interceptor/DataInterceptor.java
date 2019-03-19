package com.example.crimson.crimson.Controller.Interceptor;

import android.util.Log;

import com.example.crimson.crimson.Controller.Fragments.expense_fragment;
import com.example.crimson.crimson.Interfaces.Observer;
import java.io.IOException;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class DataInterceptor implements ClientRequestInterceptor
{

//    public String url = "http://10.53.113.195:8080";
//    public String url_wo_port = "http://10.52.243.3";
//    public String method = "GET";
//    public String u_id_str, amount_str, category_str, place_str;
//    public int load_balanced_port;
//    public int sendRequestToPort = 8080;

//    public expense_fragment exp_frag;

    public DataInterceptor()
    {
        Dispatcher.getInstance().register(this);
    }

//    @Override
//    public String getHost() {
//        return url_wo_port;
//    }
//
//    @Override
//    public void setHost(String host) {
//        url_wo_port = host;
//
//    }
//
//    @Override
//    public int getPort() {
//        return sendRequestToPort;
//    }
//
//    @Override
//    public void setPort(int port) {
//        Log.i("Port Changed From: ", ""+sendRequestToPort);
//        load_balanced_port = port;
//        Log.i("Port Changed To: ", ""+load_balanced_port);
//    }
//
//    @Override
//    public String getMethod() {
//        return "GET";
//    }
//
//    @Override
//    public void setMethod(String method) {
//        method = "GET";
//    }

    @Override
    public void onPreMarshaledRequest(final ContextObjectInterface context) {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(context.getHost()+":"+context.getPort()).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {

                if(response.isSuccessful())
                {
                    String res = response.body().string().substring(11, 15);
                    context.setPort(Integer.parseInt(res));
                }
            }
        });

    }

    @Override
    public void onPostMarshaledRequest(final MarshaledRequest context) {
        context.endTimer();
        Log.i("Latency in seconds: ", context.calculateLatency());
    }
}