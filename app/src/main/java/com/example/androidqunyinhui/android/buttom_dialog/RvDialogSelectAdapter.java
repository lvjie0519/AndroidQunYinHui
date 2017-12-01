package com.example.androidqunyinhui.android.buttom_dialog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.androidqunyinhui.R;

import java.util.List;

/**
 * Created by Administrator on 2017/11/13 0013.
 */

public class RvDialogSelectAdapter extends RecyclerView.Adapter<RvDialogSelectAdapter.ViewHolder>{

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<String> mDatas;

    public RvDialogSelectAdapter(Context mContext, List<String> datas) {
        this.mContext = mContext;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.mDatas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.mContext).inflate(R.layout.item_dialog_select , parent , false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvFilterName.setText(mDatas.get(position));

        holder.tvFilterName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (mDatas == null) ? 0:mDatas.size() ;
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvFilterName;

        public ViewHolder(View itemView) {
            super(itemView);
            initView(itemView);

        }

        private void initView(View view){
            tvFilterName = (TextView) itemView.findViewById(R.id.tv_filter_name);
        }
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

}
