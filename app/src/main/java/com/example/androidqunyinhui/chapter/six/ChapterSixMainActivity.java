package com.example.androidqunyinhui.chapter.six;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.androidqunyinhui.R;

public class ChapterSixMainActivity extends AppCompatActivity {

    private TextView textView2;

    private TextView tv3;
    private TextView tv4;

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


        tv3 = (TextView) findViewById(R.id.tv_3);
        tv4 = (TextView) findViewById(R.id.tv_4);
        tv3.setClickable(true);
        tv3.setSelected(true);
        tv4.setClickable(true);
        tv4.setSelected(false);
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("lvjie","************");
                tv3.setSelected(true);
                tv4.setSelected(false);
            }
        });
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("lvjie","##############");
                tv3.setSelected(false);
                tv4.setSelected(true);
            }
        });
    }

}
