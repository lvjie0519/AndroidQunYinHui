package com.example.androidqunyinhui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.androidqunyinhui.android.BannerTestActivity;
import com.example.androidqunyinhui.android.PicOptimizeTestActivity;
import com.example.androidqunyinhui.android.TextSpanTestActivity;
import com.example.androidqunyinhui.android.coordinatorlayout.TestMainCoordinatorLayoutActivity;
import com.example.androidqunyinhui.android.fragment.FragmentTestMainActivity;
import com.example.androidqunyinhui.android.newprocess.NewProcessTestActivity;
import com.example.androidqunyinhui.android.personface.AutioTakeActiity;
import com.example.androidqunyinhui.android.qr_code.QRCodeTestActivity;
import com.example.androidqunyinhui.android.widget.recyclerview_demo.RecyclerviewDemoActivity;
import com.example.androidqunyinhui.chapter.five.ChapterFiveMainActivity;
import com.example.androidqunyinhui.chapter.seven.ChapterSevenMainActivity;
import com.example.androidqunyinhui.chapter.six.ChapterSixMainActivity;
import com.example.androidqunyinhui.chapter.ten.ChapterTenMainActivity;
import com.example.androidqunyinhui.chapter.twelve.ChapterTwelveMainActivity;
import com.example.androidqunyinhui.data_and_time.DataAndTimeTestActivity;
import com.example.androidqunyinhui.dbflow.DbFlowUseDemoActivity;
import com.example.androidqunyinhui.event.EventBusDemoActivity;
import com.example.androidqunyinhui.glide.GlideDemoActivity;
import com.example.androidqunyinhui.jni.JniTestActivity;
import com.example.androidqunyinhui.kaifayishutanshuo.Main2Activity;
import com.example.androidqunyinhui.leak.LeakCanaryDemoActivity;
import com.example.androidqunyinhui.longpic.LongPicMainActivity;
import com.example.androidqunyinhui.lunbotu.LunBoTuActivity;
import com.example.androidqunyinhui.player.AudioPlayerDemoActivity;
import com.example.androidqunyinhui.retrofit.RetrofitMainTestActivity;
import com.example.androidqunyinhui.rxandroid.RxAndroidTestActivity;
import com.example.androidqunyinhui.selfrxandroid.RxDemoActivity;
import com.example.androidqunyinhui.self.define.view.SelfData;
import com.example.androidqunyinhui.self.define.view.TestSelfDefineViewActivity;
import com.example.androidqunyinhui.test.LrcPlayerActivity;
import com.example.androidqunyinhui.viewpager_scrollview.ViewPagerDemoActivity;
import com.example.androidqunyinhui.webview.MyWebViewTest;
import com.squareup.haha.perflib.Main;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }


    private void initView(){

        findViewById(R.id.btn_kaifayishutanshuo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Main2Activity.startActivity(MainActivity.this);
            }
        });

        findViewById(R.id.btn_self_define).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelfData.getINSTANCE().setData("lvjie");
                Toast.makeText(MainActivity.this, SelfData.getINSTANCE().getData(), Toast.LENGTH_SHORT).show();
                TestSelfDefineViewActivity.startActivity(MainActivity.this);
            }
        });

        findViewById(R.id.btn_lrc_parse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LrcPlayerActivity.startActivity(MainActivity.this);
            }
        });

        findViewById(R.id.btn_mediaplayer_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AudioPlayerDemoActivity.startActivity(MainActivity.this);
            }
        });

        findViewById(R.id.btn_chapter_five).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChapterFiveMainActivity.startActivity(MainActivity.this);
            }
        });

        findViewById(R.id.btn_chapter_six).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChapterSixMainActivity.startActivity(MainActivity.this);
            }
        });

        findViewById(R.id.btn_chapter_seven).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChapterSevenMainActivity.startActivity(MainActivity.this);
            }
        });

        findViewById(R.id.btn_chapter_ten).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChapterTenMainActivity.startActivity(MainActivity.this);
            }
        });

        findViewById(R.id.btn_chapter_twelve).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChapterTwelveMainActivity.startActivity(MainActivity.this);
            }
        });

        findViewById(R.id.btn_dbflow_use).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbFlowUseDemoActivity.startActivity(MainActivity.this);
            }
        });

        findViewById(R.id.btn_recyclerview_use).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerviewDemoActivity.startActivity(MainActivity.this);
            }
        });

        findViewById(R.id.btn_glide_use).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlideDemoActivity.startActivity(MainActivity.this);
            }
        });

        findViewById(R.id.btn_leak).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LeakCanaryDemoActivity.startActivity(MainActivity.this);
            }
        });

        findViewById(R.id.btn_viewpager_scrollview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPagerDemoActivity.startActivity(MainActivity.this);
            }
        });

        findViewById(R.id.btn_rx_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxAndroidTestActivity.startActivity(MainActivity.this);
            }
        });

        findViewById(R.id.btn_date_time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataAndTimeTestActivity.startActivity(MainActivity.this);
            }
        });
        findViewById(R.id.btn_lun_bo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LunBoTuActivity.startActivity(MainActivity.this);
            }
        });

        findViewById(R.id.btn_event_bus_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBusDemoActivity.startActivity(MainActivity.this);
            }
        });

        findViewById(R.id.btn_long_pic_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LongPicMainActivity.startActivity(MainActivity.this);
            }
        });

        findViewById(R.id.btn_vlayout_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                VlayoutTestActivity.startActivity(MainActivity.this);
//                DialogTestActivity.startActivity(MainActivity.this);
//                TextSpanTestActivity.startActivity(MainActivity.this);
                MyWebViewTest.startActivity(MainActivity.this);

            }
        });

        findViewById(R.id.btn_huadongqiantao_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 滑动嵌套
//                TestMainCoordinatorLayoutActivity.startActivity(MainActivity.this);
                // 图片优化
//                PicOptimizeTestActivity.startActivity(MainActivity.this);
                // 滑动Banner
                BannerTestActivity.startActivity(MainActivity.this);

            }
        });

        findViewById(R.id.btn_retrofit_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitMainTestActivity.startActivity(MainActivity.this);
            }
        });

        findViewById(R.id.btn_fragment_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTestMainActivity.startActivity(MainActivity.this);
            }
        });

        findViewById(R.id.btn_person_face_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutioTakeActiity.startActivity(MainActivity.this);
            }
        });

        findViewById(R.id.btn_qr_code_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QRCodeTestActivity.startActivity(MainActivity.this);
            }
        });
        findViewById(R.id.btn_jni_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewProcessTestActivity.startActivity(MainActivity.this);
            }
        });
    }

}
