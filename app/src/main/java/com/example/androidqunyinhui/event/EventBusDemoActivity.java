package com.example.androidqunyinhui.event;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androidqunyinhui.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusDemoActivity extends AppCompatActivity {

    private TextView tvShowMsg;
    private EditText etTest;

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

        etTest = (EditText) findViewById(R.id.et_test);
//        etTest.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                Log.i("lvjie", keyCode+"");
//                if(keyCode == KeyEvent.KEYCODE_DEL){
//                    Log.i("lvjie", "press on del");
//                }
//                return true;
//            }
//        });

        etTest.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("lvjie", "beforeTextChanged---"+start+"   "+count+"    "+after);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("lvjie", "onTextChanged---"+start+"   "+before+"    "+count);
                if(count == 0){
                    Log.i("lvjie", "onTextChanged---按了删除键");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

//    @Override
//    public boolean dispatchKeyEvent(KeyEvent event) {
//        if (event.getKeyCode() == KeyEvent.KEYCODE_DEL && event.getAction() != KeyEvent.ACTION_UP){
//            Log.i("lvjie", "press on del");
//        }
//        return super.dispatchKeyEvent(event);
//    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(EventMsg event) {
        Log.i("lvjie", "onDataSynEvent---收到消息...");
        tvShowMsg.setText(event.getMsg());
        EventBusPage2Activity.startActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this); // 解除订阅
    }
}
