package com.example.androidqunyinhui.viewpager_scrollview;

import android.content.Context;
import android.util.AttributeSet;
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

        return super.onInterceptTouchEvent(ev);
    }
}
