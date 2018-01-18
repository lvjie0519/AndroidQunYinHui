package com.example.androidqunyinhui;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.Arrays;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/1/15 0015.
 */

public class RxAndroidTest {

    @Test
    public void testConcatMap(){
        Observable.from(Arrays.asList(
                "http://www.baidu.com/",
                "http://www.google.com/",
                "https://www.bing.com/"))
                .concatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        return createIpObservableMultiThread(s);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("ThreadName"+Thread.currentThread().getName()+" consume  Data -> "+ s);
                    }
                });
    }

    // 获取ip
    private synchronized Observable<String> createIpObservableMultiThread(final String url) {
        return Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
//                      String ip = getIPByUrl(url);
                        System.out.println("ThreadName"+Thread.currentThread().getName()+" Emit  Data -> "+ url);
                        subscriber.onNext(url);
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io());
    }


}
