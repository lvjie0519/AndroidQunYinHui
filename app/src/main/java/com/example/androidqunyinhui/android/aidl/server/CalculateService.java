package com.example.androidqunyinhui.android.aidl.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.androidqunyinhui.CalculateInterface;

/**
 * Created by Administrator on 2018/8/26 0026.
 */

public class CalculateService extends Service{

    private static final String TAG = "CalculateService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate()...");
        super.onCreate();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind()...");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy()...");
        super.onDestroy();
    }


    private final CalculateInterface.Stub mBinder = new CalculateInterface.Stub() {

        @Override
        public int doCalAdd(int a, int b) throws RemoteException {
            Log.i(TAG, "doCalAdd()...");
            Calculate calculate = new Calculate();
            return calculate.doCalAdd(a, b);
        }
    };

}
