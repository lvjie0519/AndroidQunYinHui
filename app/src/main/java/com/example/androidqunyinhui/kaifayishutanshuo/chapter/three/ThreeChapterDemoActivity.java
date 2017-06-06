package com.example.androidqunyinhui.kaifayishutanshuo.chapter.three;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.example.androidqunyinhui.R;

/**
 * 主要是测试时间分发机制
 */
public class ThreeChapterDemoActivity extends AppCompatActivity {

    public static void startActivity(Context context){
        Intent intent = new Intent(context, ThreeChapterDemoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_chapter_demo);
    }

}
