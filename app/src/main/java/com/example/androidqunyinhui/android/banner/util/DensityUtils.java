package com.example.androidqunyinhui.android.banner.util;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by caijj on 2017/8/16.
 */
public class DensityUtils {

    public DensityUtils() {
    }

    public static int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(1, (float)dp, Resources.getSystem().getDisplayMetrics());
    }

    public static int pxToDp(float px) {
        return (int) TypedValue.applyDimension(0, px, Resources.getSystem().getDisplayMetrics());
    }
}
