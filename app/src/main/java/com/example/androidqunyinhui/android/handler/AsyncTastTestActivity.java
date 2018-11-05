package com.example.androidqunyinhui.android.handler;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.androidqunyinhui.R;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class AsyncTastTestActivity extends AppCompatActivity {

    private TextView tvShowInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_tast_test);

        new MyAsyncTask(null).execute("aa");
    }




    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    private static class MyAsyncTask extends AsyncTask<String, String, String> {

        private WeakReference<TextView> softReference;

        public MyAsyncTask(TextView textView) {
            softReference = new WeakReference<TextView>(textView);
        }

        // 执行后台耗时操作前被调用,通常用于进行初始化操作.
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        // 必须重写,异步执行后台线程要完成的任务,耗时操作将在此方法中完成
        @Override
        protected String doInBackground(String... params) {

            for(int i=0; i<10; i++){
                publishProgress(""+i);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            Log.i("lvjie", ""+values.length);
            if(softReference != null && softReference.get() != null){
                softReference.get().setText(values[0]);
            }
        }

        // 当doInBackground方法完成后,系统将自动调用此方法
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }


    }

}
