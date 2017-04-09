package com.example.androidqunyinhui.chapter.five;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

/**
 * Created by Administrator on 2017/4/7 0007.
 */
public class DragView extends View{

    private int windowWidth;
    private int lastX, lastY;

    private Scroller mScroller;

    public DragView(Context context) {
        super(context);
        initData(context);
    }

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context);
    }

    public DragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context);
    }

    private void initData(Context context){
        this.windowWidth = ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();

        this.mScroller = new Scroller(context);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();

        if(mScroller.computeScrollOffset()){
            ((View)getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            // 通过重绘来不断调用 computeScroll
            invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        Log.i("lvjie","lastX="+lastX+"  lastY="+lastY+"  x="+x+"  y="+y);

        int offsetX;
        int offsetY;

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:

                offsetX = x - lastX;
                offsetY = y - lastY;
                // 方法一：
//                layout(getLeft()+offsetX, getTop()+offsetY, getRight()+offsetX, getBottom()+offsetY);

                // 方法二：
//                offsetLeftAndRight(offsetX);
//                offsetTopAndBottom(offsetY);

                // 方法三：
                ((View)this.getParent()).scrollBy(-offsetX, -offsetY);


                break;
            case MotionEvent.ACTION_UP:
//                x = (int) event.getRawX();
//                int viewWidth = getRight()-getLeft();
//                if(x>(windowWidth>>2)){
//                    x = windowWidth;
//                }else{
//                    x = viewWidth;
//                }
//                offsetX = x - viewWidth;
//                offsetY = y - lastY;
//                layout(offsetX, getTop()+offsetY, viewWidth+offsetX, getBottom()+offsetY);

                View viewParent = (View)this.getParent();
                // viewParent.getScrollX()  是指获取视图中的content 所滑动的点坐标；
                mScroller.startScroll(viewParent.getScrollX(), viewParent.getScrollY(), -viewParent.getScrollX(), -viewParent.getScrollY(),1000);
                invalidate();

                break;
        }


        return true;
    }


}
