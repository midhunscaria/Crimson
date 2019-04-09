package com.example.crimson.crimson.Controller.Interceptor;

import android.util.Log;
import java.io.IOException;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class DataInterceptor implements ClientRequestInterceptor
{

    public DataInterceptor()
    {
    }

    @Override
    public void onPreMarshaledRequest(final ContextObjectInterface context) {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(context.getHost()+":"+context.getPort()).build();

        Log.i("Request Made To: ", context.getHost());

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
    public void onPostMarshaledRequest(final ContextObjectInterface context) {
        Log.i("Port Changed To: ", Integer.toString(context.getLoadBalancedPort()));
    }
}