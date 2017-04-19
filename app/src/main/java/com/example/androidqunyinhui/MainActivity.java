package com.example.androidqunyinhui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.androidqunyinhui.chapter.five.ChapterFiveMainActivity;
import com.example.androidqunyinhui.chapter.seven.ChapterSevenMainActivity;
import com.example.androidqunyinhui.chapter.six.ChapterSixMainActivity;
import com.example.androidqunyinhui.chapter.ten.ChapterTenMainActivity;
import com.example.androidqunyinhui.chapter.twelve.ChapterTwelveMainActivity;
import com.example.androidqunyinhui.self.define.view.TestSelfDefineViewActivity;
import com.example.androidqunyinhui.test.LrcPlayerActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }


    private void initView(){
        findViewById(R.id.btn_self_define).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                TestSelfDefineViewActivity.startActivity(MainActivity.this);
                LrcPlayerActivity.startActivity(MainActivity.this);
            }
        });

        findViewById(R.id.btn_chapter_five).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChapterFiveMainActivity.startActivity(MainActivity.this);
            }
        });

        findViewById(R.id.btn_chapter_six).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChapterSixMainActivity.startActivity(MainActivity.this);
            }
        });

        findViewById(R.id.btn_chapter_seven).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChapterSevenMainActivity.startActivity(MainActivity.this);
            }
        });

        findViewById(R.id.btn_chapter_ten).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChapterTenMainActivity.startActivity(MainActivity.this);
            }
        });

        findViewById(R.id.btn_chapter_twelve).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChapterTwelveMainActivity.startActivity(MainActivity.this);
            }
        });
    }

}
