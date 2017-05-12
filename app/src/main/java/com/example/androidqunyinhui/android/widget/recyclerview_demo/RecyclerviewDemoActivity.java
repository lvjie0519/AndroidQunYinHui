package com.example.androidqunyinhui.android.widget.recyclerview_demo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.androidqunyinhui.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerviewDemoActivity extends AppCompatActivity {

    private RecyclerView rvTest;
    private List<String> mDatas;
    private MyRvAdapter mAdapter;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, RecyclerviewDemoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_demo);

        generateData();
        initView();
    }

    private void generateData(){
        mDatas = new ArrayList<>();
        for(int i=1; i<=15; i++){
            mDatas.add(i+"");
        }
    }

    private void initView(){
        rvTest = (RecyclerView) findViewById(R.id.rv_test);

        mAdapter = new MyRvAdapter(this, mDatas);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvTest.setLayoutManager(layoutManager);
        rvTest.setAdapter(mAdapter);
    }

}
