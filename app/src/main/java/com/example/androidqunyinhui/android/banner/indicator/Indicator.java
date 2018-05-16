package com.example.androidqunyinhui.android.banner.indicator;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by caijj on 2017/8/16.
 */
public interface Indicator {
    View getLayout(Context var1, ViewGroup var2);

    int getGravity();

    void onPageScrolled(int var1, float var2, int var3, Object var4);

    void onPageSelected(int var1, Object var2);

    void onPageScrollStateChanged(int var1);
}
