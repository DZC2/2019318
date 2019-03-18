package com.bawei.dzc.net;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpUtils {


    private int HTTp_SUCCESS = 1000;

    private int HTTp_FAIL = 1001;

    private HttpListener httpListener;


    public OkHttpUtils get(String url){
        doHttp(url,0,null);
        return this;
    }

    public OkHttpUtils post(String url,FormBody.Builder bodyBuilder){
        doHttp(url,1,bodyBuilder);
        return this;
    }

    //网络请求
    private void doHttp(String url, int i,FormBody.Builder bodyBuilder) {

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url(url);



        if (i == 0){//get请求
            builder.get();
        }else {
            RequestBody body = new FormBody.Builder().build();
            builder.post(body);
        }

        Request request = builder.build();

        final Message message = Message.obtain();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                message.what = HTTp_FAIL;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                message.obj = response.body().string();
                message.what = HTTp_SUCCESS;
                handler.sendMessage(message);
            }
        });
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == HTTp_SUCCESS){//成功
                String data = (String) msg.obj;
                httpListener.success(data);
            }else {
                httpListener.fail();
            }
        }
    };

    public void result(HttpListener httpListener){
        this.httpListener = httpListener;
    }

    //接口回调
    public interface HttpListener{
        void success(String data);
        void fail();
    }


}
