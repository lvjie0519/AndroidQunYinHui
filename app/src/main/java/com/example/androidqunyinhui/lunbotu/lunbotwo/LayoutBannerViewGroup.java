package com.example.androidqunyinhui.lunbotu.lunbotwo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by Administrator on 2017/11/20 0020.
 */

public class LayoutBannerViewGroup extends ViewGroup{

    private int childCount;   // 指视图的个数
    private int childwidth;
    private int childHeight;

    private int startX;
    private int currentChildIndex = 0;

    private Scroller mScroller;

    public LayoutBannerViewGroup(Context context) {
        this(context, null);
    }

    public LayoutBannerViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LayoutBannerViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init(){
        mScroller = new Scroller(getContext());
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(), 0);
            invalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        childCount = getChildCount();
        if(0 == childCount){
            setMeasuredDimension(0, 0);
        }else{
            measureChildren(widthMeasureSpec, heightMeasureSpec);
            View view = getChildAt(0);
            childwidth = view.getMeasuredWidth();
            childHeight = view.getMeasuredHeight();

            int width = childwidth*childCount;
            setMeasuredDimension(width, childHeight);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(changed){
            int leftMargin = 0;
            for(int i=0; i<childCount; i++){
                View view = getChildAt(i);
                view.layout(leftMargin, 0, leftMargin+childwidth, childHeight);
                leftMargin += childwidth;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:

                if(!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                startX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:

                int moveX = (int) event.getX();
                int distance = moveX-startX;
                scrollBy(-distance, 0);
                startX = moveX;
                break;
            case MotionEvent.ACTION_UP:

                int scrollX = getScrollX();
                currentChildIndex = (scrollX+childwidth/2)/childwidth;

                if(currentChildIndex < 0){
                    currentChildIndex= 0;
                }else if(currentChildIndex>childCount-1){
                    currentChildIndex = childCount-1;
                }
                int dx = currentChildIndex*childwidth-scrollX;
                mScroller.startScroll(scrollX, 0, dx, 0);
                postInvalidate();
                break;
            default:
                break;
        }

        return true;
    }

    public int getCurrentChildIndex() {
        return currentChildIndex;
    }

    public void notifiyToPageChange(int page){
        if(page<0 || page>getChildCount()-1){
            return;
        }

        this.currentChildIndex = page;
        int scrollX = getScrollX();
        int dx = currentChildIndex*childwidth-scrollX;
        mScroller.startScroll(scrollX, 0, dx, 0);
        postInvalidate();
    }

}
