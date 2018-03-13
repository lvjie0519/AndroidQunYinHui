package com.example.androidqunyinhui.android.coordinatorlayout;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.androidqunyinhui.R;

/**
 * 在新版的support-v4兼容包里面有一个NestedScrollView控件，这个控件其实和普通的ScrollView并没有多大的区别，
 * 这个控件其实是MD（Meterial Design）中设计的一个控件，目的是跟MD中的其他控件兼容。应该说在MD中，
 * RecyclerView代替了ListView，而NestedScrollView代替了ScrollView，
 * NestedScrollView和RecyclerView可以解决嵌套滑动问题
 */
public class NestedScrollViewAndRecyclerViewActivity extends AppCompatActivity {

    public static void startActivity(Context context){
        Intent intent = new Intent(context, NestedScrollViewAndRecyclerViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_scroll_view_and_recycler_view);

        initView();
    }

    private void initView(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RvCommonAdapter(this));
    }

}
