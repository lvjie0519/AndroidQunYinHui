package com.example.androidqunyinhui.chapter.seven;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.androidqunyinhui.R;

public class ChapterSevenMainActivity extends AppCompatActivity {

    public static void startActivity(Context context){
        Intent intent = new Intent(context, ChapterSevenMainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_seven_main);
    }
}
