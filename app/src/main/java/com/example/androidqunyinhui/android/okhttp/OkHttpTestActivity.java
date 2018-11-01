package com.example.androidqunyinhui.android.okhttp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.androidqunyinhui.R;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpTestActivity extends AppCompatActivity {

    private OkHttpClient mClient;
    private Request mRequest;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, OkHttpTestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http_test);

        initData();
        initView();


    }

    private void initData(){
        mClient = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();
        mRequest = new Request.Builder().url("https://www.baidu.com").get().build();
    }

    private void initView(){
        findViewById(R.id.btn_syn_okhttp_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        synOkHttpTest();
                    }
                }).start();

            }
        });

        findViewById(R.id.btn_asyn_okhttp_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asynOkHttpTest();
            }
        });
    }

    private Call mCall;
    // 同步
    private void synOkHttpTest(){

        if(mCall == null){
            mCall = mClient.newCall(mRequest);
        }
        mCall = mClient.newCall(mRequest);

        Response response = null;
        try {
            response = mCall.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 异步
    private void asynOkHttpTest(){
        System.out.println(Thread.currentThread().getName());
        if(mCall == null){
            mCall = mClient.newCall(mRequest);
        }
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 在子线程中执行， 线程名为：OkHttp http://www.baidu.com/
                System.out.println(Thread.currentThread().getName());
                System.out.println(response.body().string());
            }
        });

    }


}
