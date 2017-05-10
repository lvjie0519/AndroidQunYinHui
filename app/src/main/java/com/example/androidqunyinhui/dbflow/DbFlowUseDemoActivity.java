package com.example.androidqunyinhui.dbflow;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.androidqunyinhui.R;

public class DbFlowUseDemoActivity extends AppCompatActivity {

    public static void startActivity(Context context){
        Intent intent = new Intent(context, DbFlowUseDemoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_flow_use_demo);
    }
}
