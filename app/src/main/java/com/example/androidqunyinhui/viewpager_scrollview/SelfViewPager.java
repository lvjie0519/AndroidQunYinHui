package com.example.androidqunyinhui.viewpager_scrollview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

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
}
