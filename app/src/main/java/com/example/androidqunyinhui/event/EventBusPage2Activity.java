package com.example.androidqunyinhui.event;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.androidqunyinhui.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusPage2Activity extends AppCompatActivity {

    private TextView tvShowMsg;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, EventBusPage2Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_page2);

        tvShowMsg = (TextView) findViewById(R.id.tv_show_msg);
        findViewById(R.id.btn_event_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBusPage1Activity.startActivity(EventBusPage2Activity.this);
            }
        });

        EventBus.getDefault().register(this); // 订阅
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(String event) {
        tvShowMsg.setText(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this); // 解除订阅
    }

}
