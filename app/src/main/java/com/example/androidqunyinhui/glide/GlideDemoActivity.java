package com.example.androidqunyinhui.glide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.example.androidqunyinhui.R;

public class GlideDemoActivity extends Activity {

    private String imgUrl = "http://r.v1.e.101.com/s/p/1016/847ed000400d4de098f434207ceeb099.jpg";

    private ImageView iv_1;
    private ImageView iv_2;
    private Animation mAnimation;
    private ImageView ivGif;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, GlideDemoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_demo);

        initView();
    }

    private void initView(){

        mAnimation = AnimationUtils.loadAnimation(this, R.anim.img_animation);
        LinearInterpolator lin = new LinearInterpolator();//设置动画匀速运动
        mAnimation.setInterpolator(lin);

        iv_1 = (ImageView) findViewById(R.id.iv_1);
        iv_2 = (ImageView) findViewById(R.id.iv_2);

        findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(GlideDemoActivity.this)
                        .load(imgUrl)
                        .transform(new GlideCircleTransform(GlideDemoActivity.this))
                        .placeholder(R.drawable.img1)
                        .animate(mAnimator)
                        .into(iv_1);
                iv_1.startAnimation(mAnimation);
            }
        });

        findViewById(R.id.btn_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(GlideDemoActivity.this)
                        .load(imgUrl)
                        .into(iv_2);
            }
        });

        ivGif = (ImageView) findViewById(R.id.iv_gif);
        findViewById(R.id.btn_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadGifPic();
            }
        });
    }


    private ViewPropertyAnimation.Animator mAnimator = new ViewPropertyAnimation.Animator() {
        @Override
        public void animate(View view) {
            Log.i("lvjie","Animator-->animate()...");
            view.startAnimation(mAnimation);
        }
    };

    private void loadGifPic(){
        Glide.with(this)
                .load("http://p1.pstatp.com/large/166200019850062839d3")
                .placeholder(R.drawable.img1)
                .into(ivGif);
    }


}
