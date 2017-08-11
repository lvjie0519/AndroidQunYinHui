package com.example.androidqunyinhui.kaifayishutanshuo.chapter.twelve;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidqunyinhui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/11 0011.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context mContext;

    private List<String> mDatas1 = new ArrayList<>();
    private List<String> mDatas2 = new ArrayList<>();

    private boolean mScrooling = false;

    public MyAdapter(Context context) {

        this.mContext = context;

        initDatas();
    }

    private void initDatas(){

        for(int i=0; i<30; i++){
            mDatas1.add("条目 1-"+i);
            mDatas2.add("条目 2-"+i);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.mContext).inflate(R.layout.item_rv_twelve , parent , false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.i("lvjie","onBindViewHolder-->position="+position);

        if(!mScrooling){
            Log.i("lvjie","onBindViewHolder-->position="+position+"   mScrooling="+mScrooling);
            holder.textView2.setText(mDatas2.get(position));
        }
        holder.textView1.setText(mDatas1.get(position));

    }

    @Override
    public int getItemCount() {
        return mDatas1.size();
    }

    public void setmScrooling(boolean mScrooling) {
        this.mScrooling = mScrooling;
    }

    public boolean ismScrooling() {
        return mScrooling;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textView1;
        private TextView textView2;

        public ViewHolder(View itemView) {
            super(itemView);

            textView1 = (TextView) itemView.findViewById(R.id.tv_text_1);
            textView2 = (TextView) itemView.findViewById(R.id.tv_text_2);
        }

    }
}
