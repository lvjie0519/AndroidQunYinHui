package com.example.androidqunyinhui.android.coordinatorlayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidqunyinhui.R;

/**
 * Created by Administrator on 2018/3/13 0013.
 */

public class RvCommonAdapter extends RecyclerView.Adapter<RvCommonAdapter.ViewHolder>{

    private Context mContext;

    public RvCommonAdapter(Context context){
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_rv_twelve, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(position+"");
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.tv_text_1);
            itemView.findViewById(R.id.tv_text_2).setVisibility(View.GONE);
        }
    }
}
