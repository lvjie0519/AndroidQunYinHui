package com.example.androidqunyinhui.viewpager_scrollview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidqunyinhui.R;

/**
 * Created by Administrator on 2017/6/1 0001.
 */
public class ScrollViewDemoFragment extends Fragment{

    private TextView textView;
    static int k = 0;

    public static ScrollViewDemoFragment newInstance(){
        ScrollViewDemoFragment fragment = new ScrollViewDemoFragment();
        return  fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_scrollview_demo, null);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView){
        textView = (TextView) rootView.findViewById(R.id.tv_1);
        textView.setText("滚动冲突吗--"+k++);

        rootView.findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPagerDemoActivity.isCanScroll = !ViewPagerDemoActivity.isCanScroll;
                Toast.makeText(getContext(), "点击我了...isCanScrool="+ViewPagerDemoActivity.isCanScroll, Toast.LENGTH_SHORT).show();
            }
        });


    }


}
