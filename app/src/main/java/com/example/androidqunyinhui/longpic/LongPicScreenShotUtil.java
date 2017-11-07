package com.example.androidqunyinhui.longpic;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/11/7 0007.
 */

public class LongPicScreenShotUtil {

    public static void saveBitmap(Bitmap bitmap, String fileName){

        String filePath = "/sdcard/longpic/";
        File file = new File(filePath + fileName +".png");
        if(file.exists()){
            file.delete();
        }

        File fileParent = new File(filePath);
        if(!fileParent.exists()){
            fileParent.mkdir();
        }

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            Log.i("LongPic", "已经保存");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(out != null){
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Bitmap getRecyclerViewBitMap(RecyclerView recyclerView){
        int h = 0;
        Bitmap bitmap;
        // 获取listView实际高度
        int count = recyclerView.getChildCount();
        for (int i = 0; i < count; i++) {
            h += recyclerView.getChildAt(i).getHeight();
        }

        // 创建对应大小的bitmap
        bitmap = Bitmap.createBitmap(recyclerView.getWidth(), h,Bitmap.Config.RGB_565);
        final Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        recyclerView.draw(canvas);

        return bitmap;
    }

    public static Bitmap getViewGroupBitmap(ViewGroup viewGroup){
        int h = 0;
        Bitmap bitmap;
        // 获取listView实际高度
        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            h += viewGroup.getChildAt(i).getHeight();
        }

        // 创建对应大小的bitmap
        bitmap = Bitmap.createBitmap(viewGroup.getWidth(), h,Bitmap.Config.RGB_565);
        final Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        viewGroup.draw(canvas);

        return bitmap;
    }

//    public static Bitmap waterMark(Bitmap res, Bitmap mark){
//        float scale = ((float) res.getWidth()) / mark.getWidth();
//        mark = ImageUtil.scaleImg(mark, scale);
//        int height = res.getHeight();
//        Canvas canvas = new Canvas(res);
//        int h = 0;
//        while (h < height + mark.getHeight()) {
//            canvas.drawBitmap(mark, 0, h, null);
//            h = h + mark.getHeight();
//        }
//        return res;
//    }

    private static Bitmap createWaterMaskBitmap(Bitmap src, Bitmap watermark,
                                                int paddingLeft, int paddingTop) {
        if (src == null) {
            return null;
        }
        int width = src.getWidth();
        int height = src.getHeight();
        //创建一个bitmap
        Bitmap newb = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
        //将该图片作为画布
        Canvas canvas = new Canvas(newb);
        //在画布 0，0坐标上开始绘制原始图片
        canvas.drawBitmap(src, 0, 0, null);
        //在画布上绘制水印图片
        canvas.drawBitmap(watermark, paddingLeft, paddingTop, null);
        // 保存
        canvas.save(Canvas.ALL_SAVE_FLAG);
        // 存储
        canvas.restore();
        return newb;
    }

    /**
     * 设置水印图片到中间
     * @param src
     * @param watermark
     * @return
     */
    public static Bitmap createWaterMaskCenter(Bitmap src, Bitmap watermark) {
        return createWaterMaskBitmap(src, watermark,
                (src.getWidth() - watermark.getWidth()) / 2,
                (src.getHeight() - watermark.getHeight()) / 2);
    }


}
