package com.example.androidqunyinhui.chapter.five;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by lvjie on 2017/4/9 0009.
 * 使用 android 提供的 ViewDragHelper 实现类似QQ侧滑栏；
 */
public class DragViewGroup extends FrameLayout{

    private ViewDragHelper mViewDragHelper;
    private View mMenuView, mMainView;


    public DragViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    public DragViewGroup(Context context) {
        super(context);

        init(context);
    }

    public DragViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mMenuView = getChildAt(0);
        mMainView = getChildAt(1);
    }

    private void init(Context context){
        // 第一个参数为this，表示该类生成的对象，他是ViewDragHelper的拖动处理对象，必须为ViewGroup。
        // 其中1.0f是敏感度参数,参数越大越敏感。
        mViewDragHelper = ViewDragHelper.create(this, 1.0f, new DragHelperCallback());
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    private class DragHelperCallback extends ViewDragHelper.Callback {

        // 何时开始检测触摸事件；
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            // 表示只有指定的mMainView被触摸到时，才具有相关处理操作；
            return mMainView == child;
        }

        // 处理垂直滑动；
        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return 0;
        }

        // 处理水平滑动;
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            Log.d("DragLayout", "clampViewPositionHorizontal " + left + "," + dx);
            return left;
        }

        // 拖动结束后调用
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);

            if(mMainView.getLeft() < 300){
                mViewDragHelper.smoothSlideViewTo(mMainView, 0, 0);
                ViewCompat.postInvalidateOnAnimation(DragViewGroup.this);
            }else{
                mViewDragHelper.smoothSlideViewTo(mMainView, 300, 0);
                ViewCompat.postInvalidateOnAnimation(DragViewGroup.this);
            }
        }
    }

    @Override
    public void computeScroll() {
        if(mViewDragHelper.continueSettling(true)){
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
