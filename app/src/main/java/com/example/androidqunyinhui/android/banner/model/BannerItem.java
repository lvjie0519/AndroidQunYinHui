package com.example.androidqunyinhui.android.banner.model;

/**
 * Created by caijj on 2017/8/16.
 */
public class BannerItem {

    private Object imageRes;
    private String title;

    public BannerItem(Object imageRes, String title) {
        this.imageRes = imageRes;
        this.title = title;
    }

    public Object getImageUrl() {
        return this.imageRes;
    }

    public String getTitle() {
        return this.title;
    }
}
