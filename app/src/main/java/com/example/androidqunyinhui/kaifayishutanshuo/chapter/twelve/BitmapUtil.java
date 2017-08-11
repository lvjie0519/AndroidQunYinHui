package com.example.androidqunyinhui.kaifayishutanshuo.chapter.twelve;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Administrator on 2017/8/10 0010.
 */

public class BitmapUtil {

    /**
     *
     * @param res       The resources object containing the image data
     * @param resId     The resource id of the image data
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap decodeSampleBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight){
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);      // 取出图片的原始宽高信息
        options.inSampleSize = calculateInsampleSize(options, reqWidth, reqHeight);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    private static int calculateInsampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {

        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if(height > reqHeight || width > reqWidth){
            final int halfHeigth = height >> 1;
            final int halfWidth = width >> 1;
            while ((halfHeigth/inSampleSize) >= reqHeight && (halfWidth/inSampleSize)>=reqWidth){
                inSampleSize = inSampleSize << 1;
            }
        }

        return  inSampleSize;

    }

}
