package com.example.androidqunyinhui.dbflow;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Administrator on 2017/5/10 0010.
 */
@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {

    //数据库名称
    public static final String NAME = "AppDatabase";
    //数据库版本号
    public static final int VERSION = 1;

}
