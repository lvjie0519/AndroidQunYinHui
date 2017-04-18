package com.example.androidqunyinhui.chapter.twelve;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Outline;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.TextView;

import com.example.androidqunyinhui.R;

public class ChapterTwelveMainActivity extends AppCompatActivity {

    private TextView tvRect;
    private TextView tvCircle;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, ChapterTwelveMainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_twelve_main);

        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initView(){
        tvRect = (TextView) findViewById(R.id.tv_rect);
        tvCircle = (TextView) findViewById(R.id.tv_circle);

        ViewOutlineProvider viewOutlineProvider1 = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0,0,view.getWidth(), view.getHeight(), 30);
            }
        };

        ViewOutlineProvider viewOutlineProvider2 = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setOval(0,0,view.getWidth(), view.getHeight());
            }
        };

        tvRect.setOutlineProvider(viewOutlineProvider1);
        tvCircle.setOutlineProvider(viewOutlineProvider2);
    }

}
