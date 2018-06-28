package com.example.androidqunyinhui.android;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;

import com.example.androidqunyinhui.R;
import com.example.androidqunyinhui.android.donghua.AnimationDemoActivity;

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
                AnimationDemoActivity.startActivity(AndroidMainActivity.this);
            }
        });
    }
}
