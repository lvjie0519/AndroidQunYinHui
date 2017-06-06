package com.example.androidqunyinhui.kaifayishutanshuo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.androidqunyinhui.R;
import com.example.androidqunyinhui.kaifayishutanshuo.chapter.three.ThreeChapterDemoActivity;
import com.example.androidqunyinhui.kaifayishutanshuo.chapter.two.ChapterTwoActivity;
import com.example.androidqunyinhui.kaifayishutanshuo.chapter.two.ParcStudent;
import com.squareup.haha.perflib.Main;

public class Main2Activity extends AppCompatActivity {

    public static void startActivity(Context context){
        Intent intent = new Intent(context, Main2Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initView();
    }

    public void initView(){

        findViewById(R.id.btn_chapter_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("lvjie","btn1.....");
                ParcStudent student = new ParcStudent("lvjie",21,"123456");
                ChapterTwoActivity.startActivity(Main2Activity.this, student);
            }
        });

        findViewById(R.id.btn_chapter_three).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThreeChapterDemoActivity.startActivity(Main2Activity.this);
            }
        });

    }

}
