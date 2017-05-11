package com.example.androidqunyinhui;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by Administrator on 2017/5/10 0010.
 */
public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(new FlowConfig.Builder(getApplicationContext()).build());
    }
}
