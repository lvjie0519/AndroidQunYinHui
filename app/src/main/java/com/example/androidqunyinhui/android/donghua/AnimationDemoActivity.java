package com.example.androidqunyinhui.android.donghua;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.androidqunyinhui.R;

public class AnimationDemoActivity extends AppCompatActivity {

    private ImageView ivLoading;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, AnimationDemoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_demo);

        initView();
    }

    private void initView(){
        ivLoading = (ImageView) findViewById(R.id.iv_loading);

        AnimationDrawable animationDrawable = (AnimationDrawable) getResources().getDrawable(R.drawable.view_loading);
        ivLoading.setBackground(animationDrawable);
        animationDrawable.start();
    }
}
