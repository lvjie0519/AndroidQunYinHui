package com.example.androidqunyinhui.self.define.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.androidqunyinhui.R;

/**
 * Created by Administrator on 2017/3/25 0025.
 */
public class SelfDefineView extends View{

    private Paint mPaint1;
    private Paint mPaint2;

    public SelfDefineView(Context context) {
        super(context);

        initPaint();
    }

    public SelfDefineView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initPaint();
    }

    public SelfDefineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initPaint();
    }

    private void initPaint(){
        mPaint1 = new Paint();
        mPaint1.setColor(Color.BLUE);
        mPaint1.setStyle(Paint.Style.FILL);

        mPaint2 = new Paint();
        mPaint2.setColor(Color.GREEN);
        mPaint2.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 绘制外层矩形
        canvas.drawRect(0,0,getMeasuredWidth(), getMeasuredHeight(), mPaint1);
        // 绘制内层矩形
        canvas.drawRect(10,10,getMeasuredWidth()-10, getMeasuredHeight()-10, mPaint2);
//        super.onDraw(canvas);
    }

    private int measureWidth(int width){
        int result = 200;       // 默认值
        int specMode = MeasureSpec.getMode(width);
        int specSize = MeasureSpec.getSize(width);

        // MeasureSpec.EXACTLY 表示xml中定义了精确值或者为match_parent
        // MeasureSpec.AT_MOST 表示xml中定义的是 wrap_content
        if(specMode == MeasureSpec.EXACTLY){
            result = specSize;
        }else if(specMode == MeasureSpec.AT_MOST){
            result = Math.min(result, specSize);
        }else if(specMode == MeasureSpec.UNSPECIFIED){
            result  = 300;
        }

        return  result;
    }

    private int measureHeight(int height){
        int result = 100;       // 默认值
        int specMode = MeasureSpec.getMode(height);
        int specSize = MeasureSpec.getSize(height);

        if(specMode == MeasureSpec.EXACTLY){
            result = specSize;
        }else if(specMode == MeasureSpec.AT_MOST){
            result = Math.min(result, specSize);
        }else if(specMode == MeasureSpec.UNSPECIFIED){
            result  = 150;
        }

        return  result;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
