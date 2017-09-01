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

public class EventBusDemoActivity extends AppCompatActivity {

    private TextView tvShowMsg;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, EventBusDemoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_demo);

        initView();
        EventBus.getDefault().register(this); // 订阅
    }

    private void initView(){
        findViewById(R.id.btn_event_page_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBusPage1Activity.startActivity(EventBusDemoActivity.this);
            }
        });

        findViewById(R.id.btn_event_page_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBusPage2Activity.startActivity(EventBusDemoActivity.this);
            }
        });

        tvShowMsg = (TextView) findViewById(R.id.tv_show_msg);
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(EventMsg event) {
        tvShowMsg.setText(event.getMsg());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this); // 解除订阅
    }
}
