package com.example.androidqunyinhui.android.banner.indicator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidqunyinhui.R;


/**
 * Created by caijj on 2017/8/16.
 */
public class CycleIndicator implements Indicator {
    private int               mSize;
    private PageIndicatorView mIndicatorView;

    public CycleIndicator(int size) {
        this.mSize = size;
    }

    public View getLayout(Context context, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.slp_e_indicator_layout, parent, false);
        this.mIndicatorView = (PageIndicatorView)view.findViewById(R.id.indicator);
        this.mIndicatorView.setCount(this.mSize);
        return view;
    }

    public int getGravity() {
        return 80;
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels, Object data) {
        this.mIndicatorView.onPageScrolled(position, positionOffset, positionOffsetPixels);
    }

    public void onPageSelected(int position, Object data) {
        this.mIndicatorView.onPageSelected(position);
    }

    public void onPageScrollStateChanged(int state) {
        this.mIndicatorView.onPageScrollStateChanged(state);
    }
}