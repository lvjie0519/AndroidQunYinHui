package com.example.androidqunyinhui.chapter.ten;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.androidqunyinhui.R;

public class ChapterTenMainActivity extends AppCompatActivity {

    public static void startActivity(Context context){
        Intent intent = new Intent(context, ChapterTenMainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_ten_main);

        initView();
    }

    private void initView(){
        findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
                int largeHeapSize = activityManager.getLargeMemoryClass();
                int heapSize = activityManager.getMemoryClass();
                Log.i("lvjie","最大内存："+largeHeapSize+"  内存："+heapSize);

            }
        });
    }


}
