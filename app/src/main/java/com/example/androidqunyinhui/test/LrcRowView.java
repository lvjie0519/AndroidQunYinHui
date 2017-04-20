package com.example.androidqunyinhui.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by lvjie on 2017/4/18 0018.
 */
public class LrcRowView extends View{

    private static final String TAG = "SelfTextView";

    private String mText;
    private String []mSplitText;        // 保存空格分割的字符串数组；
    private PointX []mSplitPointX;      // 保存每个单词的开始x坐标和结束x坐标；
    private int mDivider = 10;       // 每个单词之间的距离；
    private int mTextSize = 20;     // 默认字体大小
    private int mTextColor = Color.BLACK;

    private Paint mTextPaint;

    public LrcRowView(Context context) {
        super(context);

        init();
    }

    public LrcRowView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public LrcRowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init(){
        Log.i(TAG,"init...");
        this.mTextPaint = new Paint();
        this.mTextPaint.setTextSize(mTextSize);
        this.mTextPaint.setColor(mTextColor);
        setText("");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i(TAG,"onMeasure...");

        setSplitPointX();
        int height = measureHeight(heightMeasureSpec);
        setMeasuredDimension(this.mSplitPointX[this.mSplitPointX.length-1].endX, height);
    }

    private void setSplitPointX(){
        int len = this.mSplitText.length;
        this.mSplitPointX = new PointX[len];

        int totalX = 1;
        for(int i=0; i<len; i++){
            this.mSplitPointX[i] = new PointX();
            this.mSplitPointX[i].startX = totalX;
            totalX += this.mTextPaint.measureText(this.mSplitText[i]);
            if(i != len-1){
                totalX += this.mDivider;
            }else{
                totalX += 1;
            }
            this.mSplitPointX[i].endX = totalX;
        }

    }

    private int measureHeight(int height){
        int result = 100;       // 默认值
        int specMode = MeasureSpec.getMode(height);
        int specSize = MeasureSpec.getSize(height);

        // MeasureSpec.EXACTLY 表示xml中定义了精确值或者为match_parent
        if(specMode == MeasureSpec.EXACTLY){
            result = specSize;
        }else{
            Paint.FontMetrics fm = this.mTextPaint.getFontMetrics();
            result = (int) (fm.bottom-fm.top)+1;
        }

        return  result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.i(TAG,"onDraw...");

        drawText(canvas);
    }

    private void drawText(Canvas canvas){
        if(TextUtils.isEmpty(this.mText)){
            return;
        }

        int len = this.mSplitText.length;
        for(int i=0; i<len; i++){
            canvas.drawText(this.mSplitText[i], this.mSplitPointX[i].startX, this.mTextPaint.getTextSize(), this.mTextPaint);
        }
    }

    public void setTextColor(int color, boolean isInvalidate){
        if(mTextColor == color){
            return;
        }
        Log.i(TAG,"restart onDraw...mTextColor="+mTextColor+"   color="+color);
        mTextColor = color;
        this.mTextPaint.setColor(mTextColor);
        if(isInvalidate){
            invalidate();
        }
    }

    public void setTextSize(int size){
        if(mTextSize == size){
            return;
        }
        mTextSize = size;
        this.mTextPaint.setTextSize(mTextSize);
    }

    public void setText(String str){
        this.mText = (null==str) ? "": str.trim();

        this.mSplitText = this.mText.split("\\s+");
    }

    public void setDivider(int divider) {
        this.mDivider = divider;
    }

    // TODO 当发生移动超过一定范围则，点击无效
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_UP){
            int x = (int) event.getX();
            int len = this.mSplitPointX.length;
            int positon = 0;
            boolean flag = false;
            for(int i=0; i<len; i++){
                if(x>this.mSplitPointX[i].startX && x<this.mSplitPointX[i].endX-mDivider){
                    positon = i;
                    flag = true;
                    break;
                }
            }
            Log.i(TAG,"flag="+flag+"  positon="+positon);
            if(flag && this.mClick != null){
                this.mClick.onClick(this.mSplitText[positon]);
            }

        }

        return true;
    }

    private OnClickListener mClick;

    public void setOnClickListener(OnClickListener click) {
        this.mClick = click;
    }

    public interface OnClickListener{
        void onClick(String text);
    }

    private class PointX{
        public int startX;
        public int endX;
    }

}
