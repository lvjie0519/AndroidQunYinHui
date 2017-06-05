package com.example.androidqunyinhui.rxandroid;

/**
 * Created by Administrator on 2017/6/5 0005.
 */
public class Observable<T> {

    private OnSubscrible<T> onSubscrible;

    public Observable(OnSubscrible<T> onSubscrible){
        this.onSubscrible = onSubscrible;
    }

    public static <T> Observable<T> create(OnSubscrible<T> f) {
        return new Observable<>(f);
    }

    public void subscrible(Subscrible<? super T> subscrible){
        onSubscrible.call(subscrible);
    }

}
