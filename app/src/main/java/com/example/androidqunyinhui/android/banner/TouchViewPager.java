package com.example.androidqunyinhui.android.banner;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2017/8/16.
 */

public class TouchViewPager extends ViewPager {
    private onTouchListener mListener;

    public TouchViewPager(Context context) {
        super(context);
    }

    public TouchViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        this.mListener.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

    public void setListener(onTouchListener listener) {
        this.mListener = listener;
    }

    public interface onTouchListener {
        void onTouchEvent(MotionEvent var1);
    }
}
