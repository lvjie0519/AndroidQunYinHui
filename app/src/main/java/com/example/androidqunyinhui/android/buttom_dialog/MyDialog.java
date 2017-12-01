package com.example.androidqunyinhui.android.buttom_dialog;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidqunyinhui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/1 0001.
 */

public class MyDialog extends Dialog {

    private View mRootView;
    private TextView tvCancel;
    private RecyclerView rvCatalogFilter;
    private List<String> mDatas;
    private RvDialogSelectAdapter mAdapter;

    private int mScreenWidth;

    public MyDialog(@NonNull Context context,int screenWidth) {
        this(context, R.style.DialogStyle, screenWidth);
    }

    public MyDialog(@NonNull Context context, @StyleRes int themeResId, int screenWidth) {
        super(context, themeResId);

        this.mScreenWidth = screenWidth;
        init();
    }

    private void init(){
        initData();
        initDialog();
        initView();
        bindListenr();
    }

    private void initData(){
        mDatas = new ArrayList<>();
        for(int i=0; i<6; i++){
            mDatas.add("按钮 "+i);
        }
    }

    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    private void initDialog(){
        Window dialogWindow = this.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);// 显示在底部
        dialogWindow.setWindowAnimations(R.style.DialogAnimation); // 添加动画
        this.mRootView = getLayoutInflater().inflate(R.layout.dialog_catalog_filter, null);
        this.setContentView(this.mRootView);

        this.setCancelable(true);
        this.setCanceledOnTouchOutside(true);

        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.width = this.mScreenWidth; //设置宽度
        this.getWindow().setAttributes(lp);
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    private void initView(){
        this.tvCancel = (TextView) this.mRootView.findViewById(R.id.tv_cancel);
        this.rvCatalogFilter = (RecyclerView) this.mRootView.findViewById(R.id.rv_catalog_filter);
        this.rvCatalogFilter.setLayoutManager(new GridLayoutManager(getContext(), 2));

        this.mAdapter = new RvDialogSelectAdapter(getContext(), mDatas);
        this.rvCatalogFilter.setAdapter(mAdapter);
    }

    private void bindListenr(){
        this.tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        this.mAdapter.setOnItemClickListener(new RvDialogSelectAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getContext(), mDatas.get(position), Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
    }

}
