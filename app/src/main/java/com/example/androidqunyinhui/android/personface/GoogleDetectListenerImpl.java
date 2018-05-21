package com.example.androidqunyinhui.android.personface;

import android.content.Context;
import android.hardware.Camera;
import android.os.Handler;
import android.os.Message;

/**
 * Created by Administrator on 2018/5/21 0021.
 */

public class GoogleDetectListenerImpl implements Camera.FaceDetectionListener{

    private Handler mHandler;///用于向主线程发送信息
    private Context mContext;

    public GoogleDetectListenerImpl(Context mContext,Handler mHandler) {
        this.mHandler = mHandler;
        this.mContext = mContext;
    }

    @Override
    public void onFaceDetection(Camera.Face[] faces, Camera camera) {
        if (faces!=null){
            Message msg = mHandler.obtainMessage();
            msg.what = 2;
            msg.obj = faces;
            msg.sendToTarget();
        }

    }

}
