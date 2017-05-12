package com.example.androidqunyinhui.android.widget.recyclerview_demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.androidqunyinhui.R;

import java.util.List;

/**
 * Created by Administrator on 2017/5/12 0012.
 */
public class MyRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private List<String> mDatas;

    public MyRvAdapter(Context context, List<String> datas) {
        mContext = context;
        mDatas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("lvjie","onCreateViewHolder()...");
        MyRvViewHolder holder = new MyRvViewHolder(LayoutInflater.from(mContext).inflate(R.layout.my_rv_item, parent, false));
        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Log.i("lvjie","onBindViewHolder()..."+position);

        if(holder instanceof MyRvViewHolder){
            MyRvViewHolder holder1 = (MyRvViewHolder) holder;
            holder1.tvData.setText(mDatas.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mDatas==null ? 0 : mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    class MyRvViewHolder extends RecyclerView.ViewHolder{

        TextView tvData;

        public MyRvViewHolder(View itemView) {
            super(itemView);

            tvData = (TextView) itemView.findViewById(R.id.tv_data);
        }
    }

}
