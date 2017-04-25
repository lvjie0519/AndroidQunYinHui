package com.example.androidqunyinhui.self.define.view;

import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidqunyinhui.R;
import com.example.androidqunyinhui.test.LrcRowView;

public class TestSelfDefineViewActivity extends AppCompatActivity {

    private LinearLayout layoutParent;
    private LinearLayout layoutChild;
    private TextView tvTouchMe;

    private LrcRowView selfTextView;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, TestSelfDefineViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_self_define_view);
        Log.i("lvjie","onCreate...");
        initView();
    }

    private void initView(){
        this.layoutParent = (LinearLayout) findViewById(R.id.layout_parent);
        this.layoutChild = (LinearLayout) findViewById(R.id.layout_child);
        this.tvTouchMe = (TextView) findViewById(R.id.tv_touch_me);

        this.layoutParent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("lvjie","layoutParent-->onTouch()...");
                return true;
            }
        });

        this.layoutChild.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("lvjie","layoutChild-->onTouch()...");
                // 返回false表示事件还可以继续传递下去；
                return false;
            }
        });

        this.tvTouchMe.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("lvjie","tvTouchMe-->onTouch()...");
                return true;
            }
        });

        this.selfTextView = (LrcRowView) findViewById(R.id.self_text_view);
        this.selfTextView.setText("aa bb cccc   dddd  jjjjj  我爱  Java");
        this.selfTextView.setTextSize(30);
        this.selfTextView.setDivider(10);
        this.selfTextView.setTextColor(getResources().getColor(R.color.colorAccent), false);

        this.selfTextView.setOnClickListener(new LrcRowView.OnClickListener() {
            @Override
            public void onClick(String text) {
                Log.i("lvjie","text="+text);
                Toast.makeText(TestSelfDefineViewActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selfTextView.setTextColor(getResources().getColor(R.color.colorPrimary), true);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("lvjie","onStart...");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("lvjie","onRestart...");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("lvjie","onResume...");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.i("lvjie","onSaveInstanceState...");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("lvjie","onPause...");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("lvjie","onStop...");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("lvjie","onDestroy...");
    }
}
