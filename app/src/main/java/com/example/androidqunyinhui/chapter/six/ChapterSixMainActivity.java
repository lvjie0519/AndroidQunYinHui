package com.example.androidqunyinhui.chapter.six;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.androidqunyinhui.R;

public class ChapterSixMainActivity extends AppCompatActivity {

    private TextView textView2;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, ChapterSixMainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_six_main);

        initView();
    }

    private void initView(){
        textView2 = (TextView) findViewById(R.id.tv_2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
