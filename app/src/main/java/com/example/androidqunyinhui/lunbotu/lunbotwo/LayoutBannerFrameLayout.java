package com.example.androidqunyinhui.lunbotu.lunbotwo;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/11/20 0020.
 */

public class LayoutBannerFrameLayout extends FrameLayout{

    public LayoutBannerFrameLayout(@NonNull Context context) {
        this(context, null);
    }

    public LayoutBannerFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LayoutBannerFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init(){
        initLayoutBannerViewGroup();
        initDotLinearlayout();
    }

    private void initLayoutBannerViewGroup(){
        LayoutBannerViewGroup layoutBannerViewGroup = new LayoutBannerViewGroup(getContext());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutBannerViewGroup.setLayoutParams(params);
        addView(layoutBannerViewGroup);
    }

    private void initDotLinearlayout(){
        LinearLayout linearLayout = new LinearLayout(getContext());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 40);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setBackgroundColor(Color.WHITE);

        addView(linearLayout);

        FrameLayout.LayoutParams layoutParams = (LayoutParams) linearLayout.getLayoutParams();
        layoutParams.gravity = Gravity.BOTTOM;
        linearLayout.setLayoutParams(layoutParams);

        // 底部透明度

    }

}
