package com.example.androidqunyinhui.jni;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.androidqunyinhui.R;

public class JniTestActivity extends AppCompatActivity {

    public static void startActivity(Context context){
        Intent intent = new Intent(context, JniTestActivity.class);
        context.startActivity(intent);
    }


    private TextView tvShowInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jni_test);

        initView();
    }

    private void initView(){

        tvShowInfo = (TextView) findViewById(R.id.tv_show_info);

        findViewById(R.id.btn_show_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int pid = android.os.Process.myPid();
                String processName = "";
                ActivityManager manager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
                for (ActivityManager.RunningAppProcessInfo process: manager.getRunningAppProcesses()) {
                    if(process.pid == pid) {
                        processName = process.processName;
                        break;
                    }
                }
                Log.i("lvjie", "processName="+processName);

                String text = MyFirstJniTest.getString();
                int sum = MyFirstJniTest.add(1, 2);
                tvShowInfo.setText(text+"   "+sum);
            }
        });
    }
}
