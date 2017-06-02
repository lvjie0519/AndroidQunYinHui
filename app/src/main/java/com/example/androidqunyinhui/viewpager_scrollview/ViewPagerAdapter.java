package com.example.androidqunyinhui.viewpager_scrollview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/1 0001.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList= new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);

        for(int i=0; i<5; i++){
            fragmentList.add(ScrollViewDemoFragment.newInstance());
        }
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
