package com.example.androidqunyinhui.android;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;

import com.example.androidqunyinhui.R;
import com.example.androidqunyinhui.android.aidl.client.CalculateClientActivity;
import com.example.androidqunyinhui.android.donghua.AnimationDemoActivity;
import com.example.androidqunyinhui.android.donghua.PropertyAnimationDemoActivity;
import com.example.androidqunyinhui.android.donghua.TweenAnimationDemoActivity;
import com.example.androidqunyinhui.android.handler.AsyncTastTestActivity;
import com.example.androidqunyinhui.android.handler.HandlerTestActivity;
import com.example.androidqunyinhui.android.okhttp.OkHttpTestActivity;

public class AndroidMainActivity extends AppCompatActivity {

    public static void startActivity(Context context){
        Intent intent = new Intent(context, AndroidMainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_main);

        initView();
    }

    private void initView(){
        findViewById(R.id.btn_animation_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AnimationDemoActivity.startActivity(AndroidMainActivity.this);
//                TweenAnimationDemoActivity.startActivity(AndroidMainActivity.this);
                PropertyAnimationDemoActivity.startActivity(AndroidMainActivity.this);
            }
        });

        findViewById(R.id.btn_handler_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HandlerTestActivity.startActivity(AndroidMainActivity.this);
            }
        });

        findViewById(R.id.btn_aidl_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalculateClientActivity.startActivity(AndroidMainActivity.this);
            }
        });

        findViewById(R.id.btn_okhttp_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpTestActivity.startActivity(AndroidMainActivity.this);
            }
        });

        findViewById(R.id.btn_asynctast_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AndroidMainActivity.this, AsyncTastTestActivity.class));
            }
        });
    }
}
