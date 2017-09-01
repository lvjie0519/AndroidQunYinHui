package com.example.androidqunyinhui.event;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.androidqunyinhui.R;

import org.greenrobot.eventbus.EventBus;

public class EventBusPage1Activity extends AppCompatActivity {

    public static void startActivity(Context context){
        Intent intent = new Intent(context, EventBusPage1Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_page1);
        initView();
    }

    private void initView(){
        findViewById(R.id.btn_post_event).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventMsg msg = new EventMsg();
                msg.setMsg("hello eventBus");
                EventBus.getDefault().post(msg);
            }
        });
    }

}
