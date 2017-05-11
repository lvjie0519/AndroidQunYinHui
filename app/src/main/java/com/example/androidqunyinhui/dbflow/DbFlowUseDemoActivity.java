package com.example.androidqunyinhui.dbflow;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androidqunyinhui.R;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;
import java.util.Random;

public class DbFlowUseDemoActivity extends AppCompatActivity {

    private TextView tvDbShow;
    private EditText etDbInputInfo;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, DbFlowUseDemoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_flow_use_demo);

        initView();
    }

    private void initView(){
        tvDbShow = (TextView) findViewById(R.id.tv_db_show);
        etDbInputInfo = (EditText) findViewById(R.id.et_db_input);
        findViewById(R.id.btn_db_select_sigle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectOneInfo();
            }
        });
        findViewById(R.id.btn_db_select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAllInfo();
            }
        });
        findViewById(R.id.btn_db_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInfo();
            }
        });
        findViewById(R.id.btn_db_alter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alterInfo();
            }
        });
        findViewById(R.id.btn_db_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteInfo();
            }
        });
    }

    private void deleteInfo() {
        String temp = etDbInputInfo.getText().toString();
        SQLite.delete(TbStudent.class).where(TbStudent_Table.name.eq(temp)).execute();
        Log.i("lvjie","deleteInfo..."+"   "+temp);
    }

    private void alterInfo() {
        String temp = etDbInputInfo.getText().toString();
        try {
            SQLite.update(TbStudent.class).set(TbStudent_Table.name.eq(temp+" alter")).where(TbStudent_Table.name.eq(temp)).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("lvjie","alterInfo..."+temp+"   "+temp);
    }

    private void addInfo() {
        String temp = etDbInputInfo.getText().toString();
        TbStudent userInfo = new TbStudent();
        userInfo.setId(123l);
        userInfo.setName(temp);
        userInfo.setSex(0);
        userInfo.save();

        Log.i("lvjie","addInfo..."+"   "+temp);
    }

    private void selectAllInfo() {
        List<TbStudent> students = new Select().from(TbStudent.class).queryList();
        tvDbShow.setText(students.toString());
        Log.i("lvjie","selectAllInfo...");
    }

    private void selectOneInfo() {
        String temp = etDbInputInfo.getText().toString();
        List<TbStudent> userInfos = new Select().from(TbStudent.class).where(TbStudent_Table.name.eq(temp)).queryList();
        tvDbShow.setText(userInfos.toString());
        Log.i("lvjie","selectOneInfo..."+temp);
    }


}
