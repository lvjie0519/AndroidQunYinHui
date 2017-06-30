package com.example.androidqunyinhui.kaifayishutanshuo.chapter.eight;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidqunyinhui.R;

public class EightChapterActivity extends Activity {

    public static void startActivity(Context context){
        Intent intent = new Intent(context, EightChapterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eight_chapter);

        initView();
    }

    private void initView(){

        findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingButton();
                Toast.makeText(EightChapterActivity.this, "点击了...", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void floatingButton(){
        final Button floatingBtn = new Button(this);
        floatingBtn.setText("看我漂浮");
        final WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT, 0, 0, PixelFormat.TRANSPARENT);
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
        layoutParams.gravity = Gravity.LEFT|Gravity.TOP;
        layoutParams.x = 100;
        layoutParams.y = 300;
        this.getWindowManager().addView(floatingBtn, layoutParams);

        final Button button = new Button(this);
        button.setText("hello btn");

        floatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EightChapterActivity.this, "floatingBtn...", Toast.LENGTH_SHORT).show();
            }
        });


//        floatingBtn.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                int x = (int) event.getRawX();
//                int y = (int) event.getRawY();
//
//                switch (event.getAction()){
//                    case MotionEvent.ACTION_MOVE:
//                        layoutParams.x = x;
//                        layoutParams.y = y;
//                        EightChapterActivity.this.getWindowManager().updateViewLayout(floatingBtn, layoutParams);
//                        break;
//                    default:
//                        break;
//                }
//
//                return false;
//            }
//        });

    }

}
