package com.example.androidqunyinhui.longpic;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import com.example.androidqunyinhui.R;

import java.util.ArrayList;
import java.util.List;

public class LongPicMainActivity extends AppCompatActivity {

    private Button btnCreateLongPic;
    private RecyclerView recyclerView;
    private LongPicAdatper mAdatper;
    private List<String> mDatas = new ArrayList<>();

    private ScrollView scrollView;

    private int showType = 0;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, LongPicMainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_pic_main);

        initView();
    }

    private void initView(){
        generateData();

        btnCreateLongPic = (Button) findViewById(R.id.btn_create_long_pic);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mAdatper = new LongPicAdatper(this, mDatas);
        recyclerView.setAdapter(mAdatper);
        recyclerView.setLayoutManager(layoutManager);

        scrollView = (ScrollView) findViewById(R.id.scrollView);

        findViewById(R.id.btn_show_recycleview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setVisibility(View.VISIBLE);
                scrollView.setVisibility(View.INVISIBLE);
                showType = 1;
            }
        });
        findViewById(R.id.btn_show_scrollview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setVisibility(View.INVISIBLE);
                scrollView.setVisibility(View.VISIBLE);
                showType = 2;
            }
        });
        findViewById(R.id.btn_create_water_long_pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setVisibility(View.INVISIBLE);
                scrollView.setVisibility(View.VISIBLE);
                showType = 3;
            }
        });
        btnCreateLongPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(showType == 1){
                    LongPicScreenShotUtil.saveBitmap(LongPicScreenShotUtil.getRecyclerViewBitMap(recyclerView), "pic");
                }else if(showType == 2){
                    LongPicScreenShotUtil.saveBitmap(LongPicScreenShotUtil.getViewGroupBitmap(scrollView), "pic");
                }else if(showType == 3){
                    LongPicScreenShotUtil.saveBitmap(LongPicScreenShotUtil.createWaterMaskCenter(
                                    LongPicScreenShotUtil.getViewGroupBitmap(scrollView),
                            BitmapFactory.decodeResource(getResources(), R.drawable.twelve_bg)), "pic");
                }

            }
        });
    }

    private void generateData(){
        for(int i=0; i<40; i++){
            mDatas.add("数据 "+i);
        }
    }


}
