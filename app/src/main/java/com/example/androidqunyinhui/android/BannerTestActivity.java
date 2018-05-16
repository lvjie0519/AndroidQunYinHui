package com.example.androidqunyinhui.android;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.androidqunyinhui.R;
import com.example.androidqunyinhui.android.banner.BannerView;
import com.example.androidqunyinhui.android.banner.model.BannerItem;

import java.util.ArrayList;
import java.util.List;

public class BannerTestActivity extends AppCompatActivity {

    public static void startActivity(Context context){
        Intent intent = new Intent(context, BannerTestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_test);

        initBanner();
    }

    private void initBanner(){
        BannerView mBannerView = (BannerView) findViewById(R.id.banner_view);
        List<BannerItem> bannerItems = new ArrayList<>();
        BannerItem bannerItem = new BannerItem(R.drawable.slp_sdk_home_banner_1, "");
        bannerItems.add(bannerItem);
        bannerItem = new BannerItem(R.drawable.slp_sdk_home_banner_2, "");
        bannerItems.add(bannerItem);
        bannerItem = new BannerItem(R.drawable.slp_sdk_home_banner_3, "");
        bannerItems.add(bannerItem);
        bannerItem = new BannerItem(R.drawable.slp_sdk_home_banner_4, "");
        bannerItems.add(bannerItem);
        mBannerView.setData(bannerItems);
        mBannerView.startAutoPlay();
    }

}
