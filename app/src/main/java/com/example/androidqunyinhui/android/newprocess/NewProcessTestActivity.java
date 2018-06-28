package com.example.androidqunyinhui.android.newprocess;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.androidqunyinhui.R;
import com.example.androidqunyinhui.jni.JniTestActivity;

public class NewProcessTestActivity extends AppCompatActivity {

    public static void startActivity(Context context){
        Intent intent = new Intent(context, NewProcessTestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_process_test);

        initView();
    }

    private void initView(){
        findViewById(R.id.btn_next_activity).setOnClickListener(new View.OnClickListener() {
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
                JniTestActivity.startActivity(NewProcessTestActivity.this);
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
    }

}
