package com.example.androidqunyinhui.android.donghua;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidqunyinhui.R;

/**
 * 补间动画(Tween Animation)
 */
public class TweenAnimationDemoActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;

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
        textView = (TextView) findViewById(R.id.tv_zuhe);

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.tween_animation_alpha);
        imageView.startAnimation(anim);

        // 步骤1:创建 需要设置动画的 视图View
        Animation translateAnimation = AnimationUtils.loadAnimation(this, R.anim.setlist_animation_alpha);
        // 步骤2:创建 动画对象 并传入设置的动画效果xml文件
        textView.startAnimation(translateAnimation);
    }

}
