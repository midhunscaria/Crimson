package com.example.crimson.crimson.Controller.Interceptor;

import android.util.Log;

import com.example.crimson.crimson.Controller.Fragments.expense_fragment;
import com.example.crimson.crimson.Interfaces.Observer;
import java.io.IOException;
import java.util.logging.Handler;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class DataInterceptor implements Observer, UnmarshaledRequest, MarshaledRequest {

    public String url = "http://10.53.113.195:8080";
    public String url_wo_port = "http://10.52.243.3";

    public String method = "GET";

    public String u_id_str, amount_str, category_str, place_str;
    public expense_fragment exp_frag;

    public int load_balanced_port;

    public int sendRequestToPort = 8080;

    public DataInterceptor(expense_fragment e_frag)
    {
        this.exp_frag = e_frag;

    }

    @Override
    public String getHost() {
        return url_wo_port;
    }

    @Override
    public void setHost(String host) {
        url_wo_port = host;

    }

    @Override
    public int getPort() {
        return sendRequestToPort;
    }

    @Override
    public void setPort(int port) {
        Log.i("Port Changed From: ", ""+sendRequestToPort);
        load_balanced_port = port;
        Log.i("Port Changed To: ", ""+load_balanced_port);
    }

    @Override
    public String getMethod() {
        return "GET";
    }

    @Override
    public void setMethod(String method) {
        method = "GET";
    }

    @Override
    public void MeasureLatency() {

    }

    public void update() throws IOException {

        extractData();

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(url).build();

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
                    setPort(Integer.parseInt(res));
                }
            }
        });
    }

    public void extractData()
    {
        this.u_id_str = this.exp_frag.user_id_fb;
        this.amount_str = this.exp_frag.amount_str;
        this.category_str = this.exp_frag.category;
        this.place_str = this.exp_frag.expense_place_str;
    }
}
