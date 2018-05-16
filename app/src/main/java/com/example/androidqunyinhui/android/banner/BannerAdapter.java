package com.example.androidqunyinhui.android.banner;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.androidqunyinhui.android.banner.model.BannerItem;
import com.example.androidqunyinhui.android.banner.util.PositionUtil;

import java.util.List;

/**
 * Created by caijj on 2017/8/16.
 */
public class BannerAdapter extends PagerAdapter {

    private List<View>                mViews;
    private List<BannerItem>          mBannerItems;
    private OnBannerItemClickListener mListener;
    private boolean                   mIsCycle;

    public BannerAdapter(List<View> views, List<BannerItem> bannerItems, boolean isCycle, OnBannerItemClickListener listener) {
        this.mViews = views;
        this.mBannerItems = bannerItems;
        this.mIsCycle = isCycle;
        this.mListener = listener;
    }

    public int getCount() {
        return this.mViews.size();
    }

    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public View instantiateItem(ViewGroup container, final int position) {
        View v = (View) this.mViews.get(position);
        if (this.mListener != null) {
            v.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int vPosition = position;
                    if (BannerAdapter.this.mIsCycle) {
                        vPosition = PositionUtil.getRealPosition(position, BannerAdapter.this.mViews.size());
                    }

                    BannerAdapter.this.mListener.onItemClick(vPosition);
                }
            });
        }

        BannerItem bannerItem = (BannerItem) this.mBannerItems.get(position);
        if (v instanceof ImageView) {
            ImageView imageView = (ImageView) v;
            if (bannerItem.getImageUrl() instanceof Integer) {
                imageView.setImageResource((Integer) bannerItem.getImageUrl());
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            } else if (bannerItem.getImageUrl() instanceof String) {
                Glide.with(v.getContext()).load(bannerItem.getImageUrl()).centerCrop().crossFade().into(imageView);
            }
        }

        if (v.getParent() != null) {
            container.removeView(v);
        }

        container.addView(v);
        return v;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
