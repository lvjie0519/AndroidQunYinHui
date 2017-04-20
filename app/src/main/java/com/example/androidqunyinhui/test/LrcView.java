package com.example.androidqunyinhui.test;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.example.androidqunyinhui.test.lrc.LrcRow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvjie on 2017/4/19 0019.
 */
public class LrcView extends LinearLayout{

    private Context mContext;
    private List<LrcRow> mDatas;            // 需要显示的数据
    private LrcRowView[] mLrcRowViews;      // 将数据显示在具体的页面上

    private int mDivider = 10;       // 每个单词之间的距离；
    private int mTextSize = 20;     // 默认字体大小
    private int mSelectTextColor = Color.BLUE;      // 默认选中状态字体的颜色
    private int mUnSelectTextColor = Color.BLACK;   // 默认未选中状态字体的颜色

    public LrcView(Context context) {
        super(context);

        init(context);
    }

    public LrcView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public LrcView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context){
        this.mContext = context;
        initData();
    }

    private void initData(){
        mDatas = new ArrayList<>();
        mLrcRowViews = new LrcRowView[0];
    }

    public void refreshView(){
        invalidate();
    }

    public List<LrcRow> getmDatas() {
        return mDatas;
    }

    public void setmDatas(List<LrcRow> mDatas) {
        this.mDatas = mDatas;

        setmLrcRowViews();
    }

    private void setmLrcRowViews() {
        this.removeAllViews();
        int size = this.mDatas.size();
        this.mLrcRowViews = new LrcRowView[size];
        for(int i=0; i<size; i++){
            this.mLrcRowViews[i] = new LrcRowView(this.mContext);
            this.mLrcRowViews[i].setText(this.mDatas.get(i).getContent());
            this.mLrcRowViews[i].setTextSize(this.mTextSize);
            this.mLrcRowViews[i].setDivider(this.mDivider);
            this.mLrcRowViews[i].setTextColor(this.mUnSelectTextColor, false);
            this.mLrcRowViews[i].setOnClickListener(onClickListener);
            this.addView(this.mLrcRowViews[i]);
        }
    }

    public void setmDivider(int mDivider) {
        this.mDivider = mDivider;
    }

    public void setmTextSize(int mTextSize) {
        this.mTextSize = mTextSize;
    }

    public void setmSelectTextColor(int mSelectTextColor) {
        this.mSelectTextColor = mSelectTextColor;
    }

    public void setmUnSelectTextColor(int mUnSelectTextColor) {
        this.mUnSelectTextColor = mUnSelectTextColor;
    }

    private LrcRowView.OnClickListener mClick;
    public void setOnClickListener(LrcRowView.OnClickListener click) {
        this.mClick = click;
    }

    private LrcRowView.OnClickListener onClickListener = new LrcRowView.OnClickListener(){

        @Override
        public void onClick(String text) {
            if(mClick != null){
                mClick.onClick(text);
            }
        }

    };

}
