package com.example.androidqunyinhui.android.coordinatorlayout;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.androidqunyinhui.R;

public class TestMainCoordinatorLayoutActivity extends AppCompatActivity {

    public static void startActivity(Context context){
        Intent intent = new Intent(context, TestMainCoordinatorLayoutActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_main_coordinator_layout);
        initView();
    }

    private void initView(){
        findViewById(R.id.nsview_rvview_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NestedScrollViewAndRecyclerViewActivity.startActivity(TestMainCoordinatorLayoutActivity.this);
            }
        });
    }

}
