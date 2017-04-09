package com.example.androidqunyinhui.chapter.six;

import android.content.Context;

/**
 * Created by lvjie on 2016/8/31 0031.
 * dp、sp 、 px 转换工具类
 */
public class DisplayUtil {

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     * @param context 上下文
     * @param pxValue px值
     * @return  返回dip
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) ( pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     * @param context 上下文
     * @param dipValue dip值
     * @return 返回px
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param context  上下文
     * @param pxValue  px值
     *            （DisplayMetrics类中属性scaledDensity）
     * @return  返回sp
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param context  上下文
     * @param spValue  sp值
     *            （DisplayMetrics类中属性scaledDensity）
     * @return  返回px
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
