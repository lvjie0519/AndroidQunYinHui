package com.example.androidqunyinhui.kaifayishutanshuo.chapter.two;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.androidqunyinhui.R;

public class ChapterTwoActivity extends AppCompatActivity {

    public static void startActivity(Context context, ParcStudent parcStudent){
        Intent intent = new Intent(context, ChapterTwoActivity.class);
        intent.putExtra("stu", parcStudent);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_two);

        ParcStudent student = getIntent().getParcelableExtra("stu");
        Log.i("lvjie","student:  "+student.toString());
    }
}
