package com.example.androidqunyinhui.kaifayishutanshuo.chapter.three;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.androidqunyinhui.R;

/**
 * 主要是测试时间分发机制
 */
public class ThreeChapterDemoActivity extends AppCompatActivity {

    private LinearLayout layoutParent;
    private Button btnChild;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, ThreeChapterDemoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_chapter_demo);

        initView();
    }

    public void initView(){

        layoutParent = (LinearLayout) findViewById(R.id.layout_parent);
        btnChild = (Button) findViewById(R.id.btn_child);

        btnChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printLog();
                translateBtnChild();
            }
        });

//        findViewById(R.id.myview).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i("myview","onClick....");
//            }
//        });
    }

    private void printLog(){

        int[] location1 = new int[2] ;
        btnChild.getLocationInWindow(location1); //获取在当前窗口内的绝对坐标
        int[] location2 = new int[2] ;
        btnChild.getLocationOnScreen(location2);//获取在整个屏幕内的绝对坐标

        Log.i("DemoActivity", "layoutParent--getX: "+layoutParent.getX());
        Log.i("DemoActivity", "layoutParent--getY: "+layoutParent.getY());
        Log.i("DemoActivity", "layoutParent--getLeft: "+layoutParent.getLeft());
        Log.i("DemoActivity", "layoutParent--getTop: "+layoutParent.getTop());
        Log.i("DemoActivity", "layoutParent--getTranslationX: "+layoutParent.getTranslationX());
        Log.i("DemoActivity", "layoutParent--getTranslationY: "+layoutParent.getTranslationY());
        Log.i("DemoActivity", "======================================================");
        Log.i("DemoActivity", "btnChild--getX: "+btnChild.getX());
        Log.i("DemoActivity", "btnChild--getY: "+btnChild.getY());
        Log.i("DemoActivity", "btnChild--getLeft: "+btnChild.getLeft());
        Log.i("DemoActivity", "btnChild--getTop: "+btnChild.getTop());
        Log.i("DemoActivity", "btnChild--getTranslationX: "+btnChild.getTranslationX());
        Log.i("DemoActivity", "btnChild--getTranslationY: "+btnChild.getTranslationY());
    }

    private void translateBtnChild() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(btnChild,"translationX",50.0f);
        animator.setDuration(100);
        animator.start();
    }

}
