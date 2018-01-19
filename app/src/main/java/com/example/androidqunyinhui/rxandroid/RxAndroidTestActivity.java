package com.example.androidqunyinhui.rxandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.androidqunyinhui.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxAndroidTestActivity extends Activity {

    public static void startActivity(Context context){
        Intent intent = new Intent(context, RxAndroidTestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_android_test);

        initView();
    }

    private void initView(){
        findViewById(R.id.btn_test_concatMap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testConcatMap();
            }
        });
    }

    private static int k = 0;
    private void testConcatMap(){
        List<String> listStr = new ArrayList<>();
        for(int i=0; i<20; i++){
            listStr.add("测试---"+k++);
        }

        Observable.from(listStr)
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        return createIpObservableMultiThread(s);
                    }
                }, 3)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.i("lvjie", "ThreadName: " + Thread.currentThread().getName() + " consume  Data -> " + s);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("lvjie", throwable.toString());
                    }
                });
    }

    private Observable<String> createIpObservableMultiThread(final String url) {
        return Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        Log.i("lvjie","ThreadName: "+Thread.currentThread().getName()+" Emit  Data -> "+ url+"  currentTime: "+System.currentTimeMillis());
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        subscriber.onNext(url);
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io());
    }

}
