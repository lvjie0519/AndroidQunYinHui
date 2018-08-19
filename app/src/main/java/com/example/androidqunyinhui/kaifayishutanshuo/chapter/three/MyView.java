package com.example.androidqunyinhui.kaifayishutanshuo.chapter.three;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/6/6 0006.
 */
public class MyView extends android.support.v7.widget.AppCompatTextView{

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

//    @Override
//    public void scrollTo(int x, int y) {
//        super.scrollTo(x, y);
//        Log.i("MyView","scrollTo-->x="+x+"  y="+y);
//    }

    /**
     * 在dispatchTouchEvent方法中调用，用来处理点击事件，返回结果表示是否消耗当前事件，
     * 如果不消耗，则在同一个事件序列中，当前View无法再次接受到事件
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = super.onTouchEvent(event);
        Log.i("MyView","onTouchEvent-->result: "+result);
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            Log.i("MyView","onTouchEvent-->MotionEvent.ACTION_DOWN");
        }else if(event.getAction() == MotionEvent.ACTION_UP){
            Log.i("MyView","onTouchEvent-->MotionEvent.ACTION_UP");
        }else if(event.getAction() == MotionEvent.ACTION_MOVE){
            Log.i("MyView","onTouchEvent-->MotionEvent.ACTION_MOVE");
        }
        return result;
    }

}
