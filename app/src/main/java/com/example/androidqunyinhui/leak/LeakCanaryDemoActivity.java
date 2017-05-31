package com.example.androidqunyinhui.leak;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.androidqunyinhui.LeakInstance;
import com.example.androidqunyinhui.R;

public class LeakCanaryDemoActivity extends AppCompatActivity {

    static Demo sDemo;  // 会出现内存泄漏

    public static void startActivity(Context context){
        Intent intent = new Intent(context, LeakCanaryDemoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak_canary_demo);

        if(sDemo == null){
            sDemo = new Demo();
        }

    }

    class Demo {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LeakInstance.getInstance().getRefWatcher().watch(this);
    }
}
