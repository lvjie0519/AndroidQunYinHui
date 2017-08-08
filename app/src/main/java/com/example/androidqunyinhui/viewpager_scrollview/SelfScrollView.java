package com.example.androidqunyinhui.viewpager_scrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2017/6/1 0001.
 */
public class SelfScrollView extends ScrollView{



    public SelfScrollView(Context context) {
        super(context);
    }

    public SelfScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SelfScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean result = super.onInterceptTouchEvent(ev);
        Log.i("lvjie","SelfScrollView   onInterceptTouchEvent-->result="+result);
        return result;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean result = super.dispatchTouchEvent(ev);
        Log.i("lvjie","SelfScrollView   dispatchTouchEvent-->result="+result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        boolean result = super.onTouchEvent(ev);
//        result = false;
        Log.i("lvjie","SelfScrollView  onTouchEvent-->result="+result);
        return result;
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
        Log.i("lvjie","SelfScrollView   scrollTo-->x="+x+"   y="+y);
    }
}
