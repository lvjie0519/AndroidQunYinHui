package com.example.androidqunyinhui.android.aidl.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.androidqunyinhui.CalculateInterface;
import com.example.androidqunyinhui.R;

public class CalculateClientActivity extends AppCompatActivity {

    private static final String TAG = "CalculateClientActivity";

    private TextView tvShowInfo;
    private CalculateInterface mService;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, CalculateClientActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_client);

        initService();
        initView();
    }

    private void initService(){
        Bundle args = new Bundle();
        Intent intent = new Intent("com.example.aidl.calculate.CalculateService");
        intent.putExtras(args);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);

    }

    private void initView(){
        tvShowInfo = (TextView) findViewById(R.id.tv_show_info);

        findViewById(R.id.btn_getdata_from_aidl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int sum = mService.doCalAdd(10, 20);
                    tvShowInfo.setText("sum="+sum);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "onServiceConnected()....");
            mService = CalculateInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "onServiceDisconnected()....");
            mService = null;
        }
    };



}
