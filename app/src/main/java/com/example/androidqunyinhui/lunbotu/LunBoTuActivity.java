package com.example.androidqunyinhui.lunbotu;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.androidqunyinhui.R;
import com.example.androidqunyinhui.lunbotu.lunbotwo.LunBoTwoTestActivity;

import java.util.ArrayList;
import java.util.List;

public class LunBoTuActivity extends AppCompatActivity {

    private List<ImageView> views = new ArrayList<ImageView>();
    private List<BaseInfo> infos = new ArrayList<BaseInfo>();

    private MyCycleViewPager myCycleViewPager;

    private String[] imageUrls = {"http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg",
            "http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg",
            "http://pic18.nipic.com/20111215/577405_080531548148_2.jpg",
            "http://pic15.nipic.com/20110722/2912365_092519919000_2.jpg",
            "http://pic.58pic.com/58pic/12/64/27/55U58PICrdX.jpg"};

    public static void startActivity(Context context){
        Intent intent = new Intent(context, LunBoTuActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lun_bo_tu);

        initialize();

        findViewById(R.id.btn_self_define_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LunBoTwoTestActivity.startActivity(LunBoTuActivity.this);
            }
        });
    }

    private void initialize() {

        myCycleViewPager = (MyCycleViewPager) findViewById(R.id.my_cycle_view_pager);

        for(int i = 0; i < imageUrls.length; i ++){
            ADInfo info = new ADInfo();
            info.setUrl(imageUrls[i]);
            info.setContent("图片-->" + i );
            infos.add(info);
        }

        // 将最后一个ImageView添加进来
        views.add(ViewFactory.getImageView(this, infos.get(infos.size() - 1).getUrl()));
        for (int i = 0; i < infos.size(); i++) {
            views.add(ViewFactory.getImageView(this, infos.get(i).getUrl()));
        }
        // 将第一个ImageView添加进来
        views.add(ViewFactory.getImageView(this, infos.get(0).getUrl()));

        // 设置循环，在调用setData方法前调用
        myCycleViewPager.setCycle(true);

        // 在加载数据前设置是否循环
        myCycleViewPager.setData(views, infos, mAdCycleViewListener);
        //设置轮播
        myCycleViewPager.setWheel(true);

        // 设置轮播时间，默认5000ms
        myCycleViewPager.setTime(2000);
        //设置圆点指示图标组居中显示，默认靠右
        myCycleViewPager.setIndicatorCenter();
//        cycleViewPager.setIndicatorsVisibility(View.INVISIBLE);
    }

    private CycleViewPager.ImageCycleViewListener mAdCycleViewListener = new CycleViewPager.ImageCycleViewListener() {

        @Override
        public void onImageClick(BaseInfo info, int position, View imageView) {
            if (myCycleViewPager.isCycle()) {
                position = position - 1;
                Toast.makeText(LunBoTuActivity.this,
                        "position-->" + info.getUrl(), Toast.LENGTH_SHORT)
                        .show();
            }

        }

    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        myCycleViewPager.destroy();
    }
}
