package com.example.androidqunyinhui.retrofit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.androidqunyinhui.R;
import com.example.androidqunyinhui.retrofit.model.JacksonTranslation;
import com.example.androidqunyinhui.retrofit.model.Translation;
import com.example.androidqunyinhui.retrofit.service.IApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class RetrofitMainTestActivity extends AppCompatActivity {

    private static final String BASEURL = "http://fy.iciba.com/";

    private Button retrofitBtn01;
    private TextView mTvContent;
    private ProgressBar mProgressBar;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, RetrofitMainTestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_main_test);

        initView();
    }

    private void initView(){
        mTvContent = (TextView) findViewById(R.id.tv_content);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        this.retrofitBtn01 = (Button) super.findViewById(R.id.btn_retrofit_test01);
        this.retrofitBtn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initRetrofit();
            }
        });

        findViewById(R.id.btn_retrofit_test02).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initRetrofit2();
            }
        });
    }

    public void initRetrofit() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())  // 增加返回值为Gson的支持(以实体类返回)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        IApiService apiService = retrofit.create(IApiService.class);  // 这里采用的是Java的动态代理模式

        mProgressBar.setVisibility(View.VISIBLE);

        Call<Translation> call = apiService.getTranslationByCall(); //传入我们请求的键值对的值
        call.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                mProgressBar.setVisibility(View.GONE);
                Log.i("lvjie","response.body="+response.headers().toString());
                Translation translation = response.body();
                mTvContent.setText(translation.toString());
            }

            @Override
            public void onFailure(Call<Translation> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                mTvContent.setText(t.getMessage());
            }
        });

//        apiService.getTranslationByRx()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<Translation>() {
//                    @Override
//                    public void call(Translation translation) {
//                        mProgressBar.setVisibility(View.GONE);
//                        mTvContent.setText(translation.toString());
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//                        mProgressBar.setVisibility(View.GONE);
//                        Log.e("lvjie", throwable.toString());
//                    }
//                });

    }

    public void initRetrofit2(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        IApiService apiService = retrofit.create(IApiService.class);  // 这里采用的是Java的动态代理模式

        mProgressBar.setVisibility(View.VISIBLE);

        apiService.getTranslationByRxAndJackson()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<JacksonTranslation>() {
                    @Override
                    public void call(JacksonTranslation jacksonTranslation) {
                        mProgressBar.setVisibility(View.GONE);
                        mTvContent.setText(jacksonTranslation.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mProgressBar.setVisibility(View.GONE);
                        Log.e("lvjie", throwable.toString());
                    }
                });

    }

}
