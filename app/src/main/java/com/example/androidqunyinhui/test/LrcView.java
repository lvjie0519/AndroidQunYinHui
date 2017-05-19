package com.example.androidqunyinhui.test;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.WindowManager;
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

    private int mScreenwidth = 0;          // 保存屏幕的宽度

    public LrcView(Context context) {
        this(context, null);
    }

    public LrcView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LrcView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context){
        this.mContext = context;
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        this.mScreenwidth = wm.getDefaultDisplay().getWidth();
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

    public void setmDatas(List<LrcRow> datas) {
//        this.mDatas = datas;

        this.mDatas = preProDatas(datas);
        setmLrcRowViews();
    }

    // 预处理数据
    private List<LrcRow> preProDatas(List<LrcRow> datas){
        List<LrcRow> tempDatas = new ArrayList<>();
        Paint tempPaint = new Paint();
        tempPaint.setTextSize(mTextSize);
        int size = datas.size();
        for(int i=0; i<size; i++){

            String tempContent = datas.get(i).getContent()== null?"":datas.get(i).getContent();
            String[] tempArray = tempContent.split("\\s+");
            int width = (int) tempPaint.measureText(tempContent)+mDivider*tempArray.length;

            if(width > mScreenwidth){
                // 最多只做两行的处理
                LrcRow lrcRow1 = datas.get(i);
                LrcRow lrcRow2 = new LrcRow();
                lrcRow2.setStrTime(lrcRow1.getStrTime());
                lrcRow2.setTime(lrcRow1.getTime());

                String []temp = tempContent.split("\\s+", (tempArray.length>>1)+1);

                String temp1 = tempContent.substring(0, tempContent.length()-temp[temp.length-1].length());
                String temp2 = temp[temp.length-1];

                lrcRow1.setContent(temp1);
                lrcRow2.setContent(temp2);
                tempDatas.add(lrcRow1);
                tempDatas.add(lrcRow2);
            }else{
                tempDatas.add(datas.get(i));
            }
        }

        return tempDatas;
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

    public void selectLrcRowViewByTime(long time){
        if(this.mLrcRowViews == null || this.mLrcRowViews.length==0){
            return;
        }
        if(this.mDatas == null || this.mDatas.size() == 0){
            return;
        }

        int len = this.mDatas.size();
        for(int i=0; i<len; i++){
            LrcRow current = this.mDatas.get(i);
            LrcRow next = i + 1 == this.mDatas.size() ? null : this.mDatas.get(i + 1);
            /**
             *  正在播放的时间大于current行的歌词的时间而小于next行歌词的时间， 设置要高亮的行为current行
             *  正在播放的时间大于current行的歌词，而current行为最后一句歌词时，设置要高亮的行为current行
             */
            if ((time >= current.getTime() && next != null && time < next.getTime())
                    || (time > current.getTime() && next == null)){
                clearLrcRowViewSelect();
                this.mLrcRowViews[i].setTextColor(mSelectTextColor, true);
                break;
            }
        }
    }

    public void test(int position){
        this.mLrcRowViews[position].setTextColor(mSelectTextColor, true);
    }

    private void clearLrcRowViewSelect(){
        int len = this.mLrcRowViews.length;
        for(int i=0; i<len; i++){
            this.mLrcRowViews[i].setTextColor(mUnSelectTextColor, true);
        }
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
