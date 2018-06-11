package com.example.androidqunyinhui.android.qr_code;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.androidqunyinhui.R;
import com.example.androidqunyinhui.chapter.six.DisplayUtil;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class QRCodeTestActivity extends AppCompatActivity {

    private ImageView ivQrCode;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, QRCodeTestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_test);

        initView();
    }

    private void initView(){
        ivQrCode = (ImageView) findViewById(R.id.iv_qr_code);

        findViewById(R.id.btn_create_qr_code).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createQrCode();
            }
        });
    }

    private void createQrCode(){
        Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
                Bitmap bitmap = QrCodeUtils.syncEncodeQRCode("www.baidu.com", DisplayUtil.dip2px(QRCodeTestActivity.this, 100));
                subscriber.onNext(bitmap);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
                        ivQrCode.setImageBitmap(bitmap);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }

}
