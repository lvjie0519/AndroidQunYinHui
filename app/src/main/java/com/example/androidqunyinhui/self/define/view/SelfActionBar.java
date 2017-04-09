package com.example.androidqunyinhui.self.define.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.androidqunyinhui.R;

/**
 * Created by lvjie on 2017/3/28 0028.
 */
public class SelfActionBar extends RelativeLayout{

    private int mLeftTextColor;
    private String mLeftText;

    private int mTitleTextColor;
    private String mTitleText;
    private float mTitleTextSize;

    private int mRightTextColor;
    private String mRightText;

    private Button mLeftBtn;
    private Button mRightBtn;
    private TextView mTitleView;

    private ActionBarOnclickListener mActionBarOnclickListener;


    public SelfActionBar(Context context) {
        super(context);
    }

    public SelfActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SelfActionBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        initAttributes(context, attrs);
        initView(context);
        bindClickListener();
    }

    private void initAttributes(Context context, AttributeSet attrs){
        // 取得attrs.xml文件中的对象
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SelfActionBar);

        this.mLeftText = ta.getString(R.styleable.SelfActionBar_leftText);
        this.mLeftTextColor = ta.getColor(R.styleable.SelfActionBar_leftTextColor, 0);

        this.mRightText = ta.getString(R.styleable.SelfActionBar_rightText);
        this.mRightTextColor = ta.getColor(R.styleable.SelfActionBar_rightTextColor, 0);

        this.mTitleText = ta.getString(R.styleable.SelfActionBar_title);
        this.mTitleTextColor = ta.getColor(R.styleable.SelfActionBar_titleTextColor, 0);
        this.mTitleTextSize = ta.getDimension(R.styleable.SelfActionBar_titleTextSize, 10);

        // 完成赋值，一般需要调用该方法回收避免重新创建的时候错误
        ta.recycle();
    }

    private void initView(Context context){

        this.mLeftBtn = new Button(context);
        this.mLeftBtn.setText(this.mLeftText);
        this.mLeftBtn.setTextColor(this.mLeftTextColor);

        this.mRightBtn = new Button(context);
        this.mRightBtn.setText(this.mRightText);
        this.mRightBtn.setTextColor(this.mRightTextColor);

        this.mTitleView = new TextView(context);
        this.mTitleView.setText(this.mTitleText);
        this.mTitleView.setTextColor(this.mTitleTextColor);
        this.mTitleView.setTextSize(this.mTitleTextSize);
        this.mTitleView.setGravity(Gravity.CENTER);

        LayoutParams leftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        addView(mLeftBtn, leftParams);

        LayoutParams rightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        addView(mRightBtn, rightParams);

        LayoutParams titleParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        rightParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(mTitleView, titleParams);

    }

    // 添加点击事件
    private void bindClickListener(){

        this.mLeftBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mActionBarOnclickListener != null){
                    mActionBarOnclickListener.leftOnclick();
                }
            }
        });

        this.mRightBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mActionBarOnclickListener != null){
                    mActionBarOnclickListener.rightOnclick();
                }
            }
        });

    }


    public void setmActionBarOnclickListener(ActionBarOnclickListener mActionBarOnclickListener) {
        this.mActionBarOnclickListener = mActionBarOnclickListener;
    }

    public interface ActionBarOnclickListener{
        void leftOnclick();
        void rightOnclick();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
