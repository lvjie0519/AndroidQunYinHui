package com.example.androidqunyinhui.jni;

import android.util.Log;

/**
 * Created by Administrator on 2018/6/26 0026.
 */

public class MyFirstJniTest {

    static {
        //名字必须和build.gradle中的moduleName一致
        try {
            System.loadLibrary("MyFirstJinTest");
            Log.i("JNI", "MyFirstJinTest load success");
        } catch (Exception e) {
            Log.e("JNI", "MyFirstJinTest load error");
            e.printStackTrace();
        }
    }

    public static native String getString();
    public static native int add(int a, int b);

}
