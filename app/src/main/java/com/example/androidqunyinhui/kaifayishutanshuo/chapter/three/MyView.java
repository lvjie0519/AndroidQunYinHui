package com.example.androidqunyinhui.kaifayishutanshuo.chapter.three;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/6/6 0006.
 */
public class MyView extends TextView{

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean result = super.dispatchTouchEvent(event);
        Log.i("MyView","dispatchTouchEvent-->result: "+result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = super.onTouchEvent(event);
        Log.i("MyView","onTouchEvent-->result: "+result);
        return true;
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if(event.getAction() == MotionEvent.ACTION_DOWN){
//            Log.i("MyView","MotionEvent.ACTION_DOWN");
//        }else if(event.getAction() == MotionEvent.ACTION_UP){
//            Log.i("MyView","MotionEvent.ACTION_UP");
//        }else if(event.getAction() == MotionEvent.ACTION_MOVE){
//            Log.i("MyView","MotionEvent.ACTION_MOVE");
//        }
//        return true;
//    }

}
