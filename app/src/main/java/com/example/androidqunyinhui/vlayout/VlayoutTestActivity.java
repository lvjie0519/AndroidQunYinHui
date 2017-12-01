package com.example.androidqunyinhui.vlayout;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.example.androidqunyinhui.R;

public class VlayoutTestActivity extends AppCompatActivity {

    private static final boolean BANNER_LAYOUT = true;
    private static final boolean LINEAR_LAYOUT = true;
    private static final boolean ONEN_LAYOUT = true;
    private static final boolean GRID_LAYOUT = true;
    private static final boolean STICKY_LAYOUT = true;
    private static final boolean HORIZONTAL_SCROLL_LAYOUT = true;
    private static final boolean SCROLL_FIX_LAYOUT = true;

    private RecyclerView rvVlayoutTest;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, VlayoutTestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vlayout_test);

        initView();
    }

    private void initView(){
        this.rvVlayoutTest = (RecyclerView) findViewById(R.id.rv_vlayout_test);

        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        this.rvVlayoutTest.setLayoutManager(layoutManager);

        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        this.rvVlayoutTest.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 20);

        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);
        this.rvVlayoutTest.setAdapter(delegateAdapter);


    }



}
