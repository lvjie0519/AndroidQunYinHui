package com.example.androidqunyinhui.retrofit.service;

import com.example.androidqunyinhui.retrofit.model.JacksonTranslation;
import com.example.androidqunyinhui.retrofit.model.Translation;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/8/16 0016.
 */
public interface IApiService {

    // 注解里传入 网络请求 的部分URL地址
    // Retrofit把网络请求的URL分成了两部分：一部分放在Retrofit对象里，另一部分放在网络请求接口里
    // 如果接口里的url是一个完整的网址，那么放在Retrofit对象里的URL可以忽略
    // getCall()是接受网络请求数据的方法
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    Call<Translation> getTranslationByCall();

    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    Observable<Translation> getTranslationByRx();

    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    Observable<JacksonTranslation> getTranslationByRxAndJackson();


    @GET("ajax.php")
    Observable<JacksonTranslation> getTranslationByRxAndJacksonPro(
            @Query("a") String value1,
            @Query("f") String value2,
            @Query("t") String value3,
            @Query("w") String value4
    );

}
