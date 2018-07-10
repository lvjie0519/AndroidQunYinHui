package com.example.androidqunyinhui.android.donghua;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.androidqunyinhui.R;

public class PropertyAnimationDemoActivity extends AppCompatActivity {

    private Button btn01;
    private Button btn02;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, PropertyAnimationDemoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation_demo);

        initView();
    }

    private void initView(){
        btn01 = (Button) findViewById(R.id.btn_1);
        btn02 = (Button) findViewById(R.id.btn_2);

        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1Animator();
            }
        });

        btn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn2Animator();
            }
        });
    }

    private void btn1Animator() {

        // 步骤1：设置属性数值的初始值 & 结束值
        // 初始值 = 100
        // 结束值 = 500
        // ValueAnimator.ofInt()内置了整型估值器,直接采用默认的.不需要设置
        ValueAnimator valueAnimator = ValueAnimator.ofInt(100, 500);

        // 步骤2：设置动画的播放各种属性
        // 设置动画运行时长:2s
        valueAnimator.setDuration(2000);


        // 步骤3：将属性数值手动赋值给对象的属性:此处是将 值 赋给 按钮的宽度
        // 设置更新监听器：即数值每次变化更新都会调用该方法
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {

                int currentValue = (Integer) animator.getAnimatedValue();
                // 获得每次变化后的属性值
                // 输出每次变化后的属性值进行查看
                Log.i("lvjie", "currentValue="+currentValue);


                // 每次值变化时，将值手动赋值给对象的属性
                // 即将每次变化后的值 赋 给按钮的宽度，这样就实现了按钮宽度属性的动态变化
                btn01.getLayoutParams().width = currentValue;


                // 步骤4：刷新视图，即重新绘制，从而实现动画效果
                btn01.requestLayout();

            }
        });

        // 启动动画
        valueAnimator.start();
    }

    private void btn2Animator() {
        // 载入XML动画
        ValueAnimator animator = (ValueAnimator) AnimatorInflater.loadAnimator(this, R.animator.value_animation);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int currentValue = (Integer) animation.getAnimatedValue();
                btn02.getLayoutParams().width = currentValue;
                btn02.requestLayout();
            }
        });
        // 启动动画
        animator.start();

    }

}
