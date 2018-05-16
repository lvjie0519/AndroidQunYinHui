package com.example.androidqunyinhui.android.banner.util;

/**
 * Created by caijj on 2017/8/16.
 */
public class PositionUtil {

    public PositionUtil() {
    }

    public static int getRealPosition(int position, int size) {
        int maxIndex = size - 1;
        int realPosition;
        if(position == 0) {
            realPosition = maxIndex - 2;
        } else if(position == maxIndex) {
            realPosition = 0;
        } else {
            realPosition = position - 1;
        }

        return realPosition;
    }
}
