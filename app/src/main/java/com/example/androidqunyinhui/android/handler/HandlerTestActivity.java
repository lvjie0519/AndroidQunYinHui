package com.example.androidqunyinhui.android.handler;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.androidqunyinhui.R;

public class HandlerTestActivity extends AppCompatActivity {

    private static final String TAG = "HandlerTestActivity";

    private TextView tvShowInfo;

    private MyHandler myHandler;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, HandlerTestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_test);

        initData();
        initView();
    }

    private void initData(){
        myHandler = new MyHandler();
    }

    private void initView(){
        this.tvShowInfo = (TextView) findViewById(R.id.tv_show_info);

        findViewById(R.id.btn_update_ui).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUiInThread();
            }
        });


        findViewById(R.id.btn_send_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                sendMsg();
                myThreadTest();
            }
        });
    }

    private void updateUiInThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "sendMsg--run()-->"+Thread.currentThread().getName());
                String text = "i am from thread";
                tvShowInfo.setText(text);
            }
        }).start();
    }

    private void sendMsg(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "sendMsg--run()-->"+Thread.currentThread().getName());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = new Message();
                msg.arg1 = 1001;
                Bundle bundle = new Bundle();
                bundle.putString("mydata", "hello, i'am from handler");
                msg.setData(bundle);
                myHandler.sendMessage(msg);
            }
        }).start();


    }

    MyThread myThread = new MyThread();
    private void myThreadTest(){

        myThread.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "sendMsg--run()-->"+Thread.currentThread().getName());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = new Message();
                msg.arg1 = 1001;
                Bundle bundle = new Bundle();
                bundle.putString("mydata", "hello, i'am from handler");
                msg.setData(bundle);
                myThread.mHandler.sendMessage(msg);
            }
        }).start();
    }

    class MyThread extends Thread{

        private MyHandler mHandler;

        public MyThread() {
        }

        private void init(){
            Looper.prepare();
            mHandler = new MyHandler();
            Looper.loop();
        }

        @Override
        public void run() {
            super.run();
            Log.i(TAG, "MyThread--run()-->"+Thread.currentThread().getName()+" start...");
            init();
            Log.i(TAG, "MyThread--run()-->"+Thread.currentThread().getName()+" end...");
        }
    }

    class MyHandler extends Handler{

        public MyHandler() {
        }

        public MyHandler(Looper L) {
            super(L);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.i(TAG, "MyHandler--handleMessage()-->"+Thread.currentThread().getName());
            Bundle bundle = msg.getData();
            String info = bundle.getString("mydata");
            tvShowInfo.setText(info);
        }
    }
}
