package com.example.androidqunyinhui.self.define.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidqunyinhui.R;
import com.example.androidqunyinhui.test.LrcRowView;

public class TestSelfDefineViewActivity extends AppCompatActivity {

    private LinearLayout layoutParent;
    private LinearLayout layoutChild;
    private TextView tvTouchMe;
    private EditText editText;

    private LrcRowView selfTextView;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, TestSelfDefineViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_self_define_view);

        String name = "";
        if(savedInstanceState != null){
            name = savedInstanceState.getString("name", "lvjie");
        }
        Log.i("lvjie","onCreate...name="+name);

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
                Toast.makeText(TestSelfDefineViewActivity.this, SelfData.getINSTANCE().getData(), Toast.LENGTH_SHORT).show();
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
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i("lvjie","onConfigurationChanged...");
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
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String name = "";
        if(savedInstanceState != null){
            name = savedInstanceState.getString("name", "lvjie");
        }
        Log.i("lvjie","onRestoreInstanceState...name="+name);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putString("name","jack");
        Log.i("lvjie","onSaveInstanceState...");
        super.onSaveInstanceState(outState, outPersistentState);
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
