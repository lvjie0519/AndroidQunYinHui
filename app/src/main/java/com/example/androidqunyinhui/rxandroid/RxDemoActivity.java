package com.example.androidqunyinhui.rxandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.androidqunyinhui.R;

public class RxDemoActivity extends AppCompatActivity {

    public static void startActivity(Context context){
        Intent intent = new Intent(context, RxDemoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_demo);

        initView();
    }

    private void initView(){
        findViewById(R.id.btn_self_rx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test1();
            }
        });
    }

    private void test1(){

        Observable.create(new OnSubscrible<String>() {
            @Override
            public void call(Subscrible<? super String> subscrible) {
                subscrible.onNext("hello rx");
            }
        }).subscrible(new Subscrible<String>() {
            @Override
            public void onNext(String s) {
                Toast.makeText(RxDemoActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
