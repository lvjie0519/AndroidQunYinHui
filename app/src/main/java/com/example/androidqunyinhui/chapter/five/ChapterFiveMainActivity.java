package com.example.androidqunyinhui.chapter.five;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.androidqunyinhui.R;
import com.example.androidqunyinhui.chapter.six.DisplayUtil;

public class ChapterFiveMainActivity extends AppCompatActivity {

    public static void startActivity(Context context){
        Intent intent = new Intent(context, ChapterFiveMainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_five_main);

        int px = DisplayUtil.dip2px(this, 10);
        Log.i("lvjie","px="+px);
    }
}
