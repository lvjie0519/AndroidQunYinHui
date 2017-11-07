package com.example.androidqunyinhui.longpic;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidqunyinhui.R;

import java.util.List;

/**
 * Created by Administrator on 2017/11/7 0007.
 */

public class LongPicAdatper extends RecyclerView.Adapter<LongPicAdatper.ViewHolder> {

    private Context mContext;
    private List<String> mDatas;

    public LongPicAdatper(Context context, List<String> datas) {
        this.mContext = context;
        mDatas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_long_pic, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        holder.textView.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView ;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_1);
        }
    }
}
