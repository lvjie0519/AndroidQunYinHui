package com.example.androidqunyinhui.dbflow;

import android.util.Log;

import com.raizlabs.android.dbflow.annotation.Migration;
import com.raizlabs.android.dbflow.sql.SQLiteType;
import com.raizlabs.android.dbflow.sql.migration.AlterTableMigration;

/**
 * Created by Administrator on 2017/5/11 0011.
 */
@Migration(version = AppDatabase.VERSION, database = AppDatabase.class)
public class Migration_2_TbStudent extends AlterTableMigration<TbStudent> {

    public Migration_2_TbStudent(Class<TbStudent> table) {
        super(table);
        Log.i("lvjie","Migration_2_TbStudent()...");
    }

    @Override
    public void onPreMigrate() {
        Log.i("lvjie","onPreMigrate()..."+TbStudent_Table.email.getNameAlias().getNameAsKey());
        addColumn(SQLiteType.TEXT, TbStudent_Table.email.getNameAlias().getNameAsKey());
    }

}
