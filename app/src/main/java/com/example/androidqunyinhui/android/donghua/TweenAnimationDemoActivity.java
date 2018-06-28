package com.example.androidqunyinhui.android.donghua;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.androidqunyinhui.R;

/**
 * 补间动画(Tween Animation)
 */
public class TweenAnimationDemoActivity extends AppCompatActivity {

    private ImageView imageView;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, TweenAnimationDemoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween_animation_demo);

        initView();
    }

    private void initView(){
        imageView = (ImageView) findViewById(R.id.iv_1);

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.tween_animation_alpha);
        imageView.startAnimation(anim);
    }

}
