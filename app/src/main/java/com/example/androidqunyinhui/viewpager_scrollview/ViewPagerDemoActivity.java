package com.example.androidqunyinhui.viewpager_scrollview;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.example.androidqunyinhui.R;

public class ViewPagerDemoActivity extends AppCompatActivity {

    public static boolean isCanScroll = true;

    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, ViewPagerDemoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_demo);

        initView();
    }

    private void initView(){
        viewPager = (ViewPager) findViewById(R.id.vp_demo);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(5);

        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return !isCanScroll;
            }
        });
    }

}
