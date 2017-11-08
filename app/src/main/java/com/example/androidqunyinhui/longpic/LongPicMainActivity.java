package com.example.androidqunyinhui.longpic;

import android.Manifest;
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
import android.widget.Toast;

import com.example.androidqunyinhui.R;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

public class LongPicMainActivity extends AppCompatActivity {

    private Button btnCreateLongPic;
    private RecyclerView recyclerView;
    private LongPicAdatper mAdatper;
    private List<String> mDatas = new ArrayList<>();

    private ScrollView scrollView;

    private int showType = 0;

    private RxPermissions rxPermissions;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, LongPicMainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_pic_main);

        rxPermissions = RxPermissions.getInstance(this);
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
                Toast.makeText(LongPicMainActivity.this, "设置完成", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.btn_show_pingtu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setVisibility(View.INVISIBLE);
                scrollView.setVisibility(View.VISIBLE);
                showType = 4;
                Toast.makeText(LongPicMainActivity.this, "设置完成", Toast.LENGTH_SHORT).show();
            }
        });
        btnCreateLongPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean granted) {

                        if(granted){
                            if(showType == 1){
                                LongPicScreenShotUtil.saveBitmap(LongPicScreenShotUtil.getRecyclerViewBitMap(recyclerView), "pic");
                            }else if(showType == 2){
                                LongPicScreenShotUtil.saveBitmap(LongPicScreenShotUtil.getViewGroupBitmap(scrollView), "pic");
                            }else if(showType == 3){
                                LongPicScreenShotUtil.saveBitmap(LongPicScreenShotUtil.createWaterMaskCenter(
                                        LongPicScreenShotUtil.getViewGroupBitmap(scrollView),
                                        BitmapFactory.decodeResource(getResources(), R.drawable.twelve_bg)), "pic");
                            }else if(showType == 4){
                                LongPicScreenShotUtil.saveBitmap(LongPicScreenShotUtil.mergeBitmap_TB(
                                        LongPicScreenShotUtil.getViewGroupBitmap(scrollView),
                                        BitmapFactory.decodeResource(getResources(), R.drawable.twelve_bg), true), "pic");
                            }
                            Toast.makeText(LongPicMainActivity.this, "图片生成完成: /sdcard/longpic/", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
    }

    private void generateData(){
        for(int i=0; i<40; i++){
            mDatas.add("数据 "+i);
        }
    }


}
