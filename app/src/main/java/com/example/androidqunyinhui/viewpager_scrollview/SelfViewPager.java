package com.example.androidqunyinhui.viewpager_scrollview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2017/6/1 0001.
 */
public class SelfViewPager extends ViewPager{

    private boolean isCanScroll = true;

    public SelfViewPager(Context context) {
        super(context);
    }

    public SelfViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void scrollTo(int x, int y) {
        if(isCanScroll){
            super.scrollTo(x, y);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean result = super.onInterceptTouchEvent(ev);
        Log.i("lvjie","SelfViewPager   onInterceptTouchEvent-->result="+result);
        return result;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean result = super.dispatchTouchEvent(ev);
        Log.i("lvjie","SelfViewPager   dispatchTouchEvent-->result="+result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        boolean result = super.onTouchEvent(ev);
        Log.i("lvjie","SelfViewPager   onTouchEvent-->result="+result);
        return result;
    }
}
