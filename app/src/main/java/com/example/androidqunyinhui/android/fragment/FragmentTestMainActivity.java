package com.example.androidqunyinhui.android.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.androidqunyinhui.R;

public class FragmentTestMainActivity extends AppCompatActivity {

    private FragmentTransaction mFragmentTransaction;

    private MyFirstFragment firstFragment;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, FragmentTestMainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_test_main);

        initData();
        initView();
        initFragment();
    }

    private void initData(){
        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
    }

    private void initView(){
        findViewById(R.id.btn_fragment_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentTransaction.show(firstFragment);
            }
        });

//        findViewById(R.id.btn_fragment_two).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mFragmentTransaction.hide(firstFragment);
//            }
//        });
    }

    public void initFragment(){
        firstFragment = MyFirstFragment.newInstance();
        mFragmentTransaction.add(R.id.fl_conent, firstFragment, "fragment_one");
        mFragmentTransaction.commit();
    }

}
