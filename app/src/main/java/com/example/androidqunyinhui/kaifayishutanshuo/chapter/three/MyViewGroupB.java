package com.example.androidqunyinhui.kaifayishutanshuo.chapter.three;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/6/6 0006.
 */
public class MyViewGroupB extends LinearLayout{

    public MyViewGroupB(Context context) {
        super(context);
    }

    public MyViewGroupB(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroupB(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 用来进行事件的分发，如果事件能够传递到当前View，此方法一定会执行
     * 返回的结果受当前 View的onTouchEvent和下级View的onInterceptTouchEvent方法影响，表示是否消耗当前事件
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        boolean result = super.dispatchTouchEvent(ev);
        super.dispatchTouchEvent(ev);
        boolean result = true;
        Log.i("MyViewGroupB","dispatchTouchEvent-->result: "+result);
        return result;
    }

    /**
     * 该方法只有继承ViewGroup类才有
     * 是否拦截了某个事件,同一个事件序列，只会调用一次  返回的结果表示是否拦截当前事件  true:表示拦截
     * 如果拦截，则只会执行 当前view的 onTouchEvent和dispatchTouchEvent方法
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean result = super.onInterceptTouchEvent(ev);
        Log.i("MyViewGroupB","onInterceptTouchEvent-->result: "+result);
        return result;
    }

    /**
     * 在dispatchTouchEvent方法中调用，用来处理点击事件，返回结果表示是否消耗当前事件，
     * 如果不消耗，则在同一个事件序列中，当前View无法再次接受到事件
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        boolean result = super.onTouchEvent(event);
        super.onTouchEvent(event);
        boolean result = true;
        Log.i("MyViewGroupB","onTouchEvent-->result: "+result);
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            Log.i("MyViewGroupB","onTouchEvent-->MotionEvent.ACTION_DOWN");
        }else if(event.getAction() == MotionEvent.ACTION_UP){
            Log.i("MyViewGroupB","onTouchEvent-->MotionEvent.ACTION_UP");
        }else if(event.getAction() == MotionEvent.ACTION_MOVE){
            Log.i("MyViewGroupB","onTouchEvent-->MotionEvent.ACTION_MOVE");
        }
        return result;
    }

}
