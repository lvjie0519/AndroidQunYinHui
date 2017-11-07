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


}
