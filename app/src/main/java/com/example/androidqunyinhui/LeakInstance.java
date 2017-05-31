package com.example.androidqunyinhui;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by Administrator on 2017/5/27 0027.
 */
public class LeakInstance {

    private RefWatcher refWatcher;
    private static LeakInstance instance;

    private LeakInstance(){}


    public static LeakInstance init(Context context){
        if(instance == null){
            instance = new LeakInstance();
            instance.setRefWatcher(context);
        }
        return instance;
    }

    public static LeakInstance getInstance(){
        return instance;
    }

    public RefWatcher getRefWatcher(){
        return refWatcher;
    }

    private void setRefWatcher(Context context){
        Log.i("lvjie"," LeakCanary.install()....");
        refWatcher = LeakCanary.install((Application) context.getApplicationContext());
    }

}
