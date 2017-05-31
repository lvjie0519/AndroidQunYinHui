package com.example.androidqunyinhui.glide;

import android.content.Context;
import android.content.Intent;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
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

public class GlideDemoActivity extends AppCompatActivity {

    private String imgUrl = "http://r.v1.e.101.com/s/p/1016/847ed000400d4de098f434207ceeb099.jpg";

    private ImageView iv_1;
    private Animation mAnimation;

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
    }

    private ViewPropertyAnimation.Animator mAnimator = new ViewPropertyAnimation.Animator() {
        @Override
        public void animate(View view) {
            Log.i("lvjie","Animator-->animate()...");
            view.startAnimation(mAnimation);
        }
    };


}
