package com.example.androidqunyinhui.lunbotu.lunbotwo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidqunyinhui.R;

public class LunBoTwoTestActivity extends Activity {

    private LayoutBannerViewGroup mLayoutBannerViewGroup;
    private int page = 0;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, LunBoTwoTestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lun_bo_two_test);

        initView();
    }

    private void initView(){
        mLayoutBannerViewGroup = (LayoutBannerViewGroup) findViewById(R.id.lbvg_1);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        for(int i=0; i<5; i++){
//            TextView textView = new TextView(this);
//            textView.setText("我是 "+i);
//            textView.setLayoutParams(new ViewGroup.LayoutParams(dm.widthPixels, ViewGroup.LayoutParams.MATCH_PARENT));
//            textView.setGravity(Gravity.CENTER);
//            mLayoutBannerViewGroup.addView(textView);

            View view = LayoutInflater.from(this).inflate(R.layout.item_banner, null, false);
            ((TextView)view.findViewById(R.id.tv_1)).setText("我是 "+i);
            view.setLayoutParams(new ViewGroup.LayoutParams(dm.widthPixels, ViewGroup.LayoutParams.MATCH_PARENT));
            mLayoutBannerViewGroup.addView(view);

        }

        findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LunBoTwoTestActivity.this, ""+mLayoutBannerViewGroup.getCurrentChildIndex(), Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.btn_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLayoutBannerViewGroup.notifiyToPageChange(++page);
            }
        });
    }

}
