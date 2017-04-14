package com.example.androidqunyinhui.chapter.seven;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidqunyinhui.R;

public class ChapterSevenMainActivity extends AppCompatActivity {

    private ImageView imageView1;
    private TextView textView1;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, ChapterSevenMainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_seven_main);

        initView();
    }

    private void initView(){

        findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                initAlphaAnimation();
//                initRotateAnimation();
                initTranslateAnimation();
            }
        });


        textView1 = (TextView) findViewById(R.id.tv_1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("lvjie","textView1.....");
                initObjectAnimator(v);
            }
        });

    }

    // 透明度动画
    private void initAlphaAnimation(){
        imageView1 = (ImageView) findViewById(R.id.iv_1);
        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(2000);
        imageView1.startAnimation(aa);
    }

    // 旋转动画
    private void initRotateAnimation(){
        imageView1 = (ImageView) findViewById(R.id.iv_1);
        // 开始角度   结束角度   旋转点的x坐标， 有坐标
//        RotateAnimation ra = new RotateAnimation(0, 360, 200, 200);
        // 围绕自身中心点旋转
        RotateAnimation ra = new RotateAnimation(0, 360, RotateAnimation.RELATIVE_TO_SELF,0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(2000);
        imageView1.startAnimation(ra);
    }

    // 移动动画
    private void initTranslateAnimation(){
        imageView1 = (ImageView) findViewById(R.id.iv_1);

        // X开始 X结束 Y开始  Y结束
        TranslateAnimation ta = new TranslateAnimation(0, 200, 0, 300);
        ta.setDuration(2000);
        imageView1.startAnimation(ta);

    }

    private void initObjectAnimator(View view){
        // 后面是一个数组，表示要移动的距离。  为一个参数的时候，表示从初始位置移动到第一个参数的位置，
        // 两个参数的时候表示从第一个参数的位置移动到第二个参数的位置，三个：第一个移动到第二个再到第三个。
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", 300, 100);
        animator.setDuration(3000);
        animator.start();
    }

}