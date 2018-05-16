package com.example.androidqunyinhui.android.banner;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.androidqunyinhui.android.banner.indicator.CycleIndicator;
import com.example.androidqunyinhui.android.banner.indicator.Indicator;
import com.example.androidqunyinhui.android.banner.model.BannerItem;
import com.example.androidqunyinhui.android.banner.util.PositionUtil;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by caijj on 2017/8/16.
 */
public class BannerView extends FrameLayout implements ViewPager.OnPageChangeListener {

    private static final String  TAG     = "BannerView";
    private static final boolean DEBUG   = false;
    private              Params  mParams = new Params();
    private Indicator mIndicator;
    private OnBannerItemClickListener mListener;
    private TouchViewPager            mViewPager;
    private BannerAdapter             mAdapter;
    private LinkedList<BannerItem> mBannerItems = new LinkedList();
//    private BannerItemsComparator mBannerItemsComparator;
    private Subscription          mAutoPlayModeSubscription;

    public BannerView(Context context) {
        super(context);
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public BannerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.init();
    }

    private void init() {
        this.mViewPager = new TouchViewPager(this.getContext());
        this.mViewPager.addOnPageChangeListener(this);
        this.mViewPager.setListener(new TouchViewPager.onTouchListener() {
            public void onTouchEvent(MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case 0:
                        if (BannerView.this.mParams.autoPlayMode) {
                            BannerView.this.stopAutoPlayInternal();
                        }
                        break;
                    case 1:
                    case 6:
                        if (BannerView.this.mParams.autoPlayMode) {
                            BannerView.this.stopAutoPlayInternal();
                            BannerView.this.startAutoPlayInternal();
                        }
                }

            }
        });
        this.addView(this.mViewPager, new LayoutParams(-1, -1));
//        this.mBannerItemsComparator = new BannerItemsComparator();
        if (!this.mParams.cycleMode) {
            this.mParams.initPosition = 0;
        }

    }

    public void setData(List<BannerItem> bannerItems) {
        if (!this.hasIndicator()) {
            this.setIndicator(new CycleIndicator(bannerItems.size()));
        }

        LinkedList newBannerItems = new LinkedList(bannerItems);
        if (this.mParams.cycleMode) {
            this.fillCycleModeData(newBannerItems);
        }

//        if (!this.mBannerItemsComparator.isEqual(this.mBannerItems, newBannerItems)) {
            this.mBannerItems.clear();
            this.mBannerItems.addAll(bannerItems);
            this.refresh();
//        }

    }

    public void refresh() {
        if (this.mBannerItems.size() > 1 && this.mParams.cycleMode) {
            this.fillCycleModeData(this.mBannerItems);
        }

        LinkedList views = new LinkedList();
        Iterator var2 = this.mBannerItems.iterator();

        while (var2.hasNext()) {
            BannerItem bannerItem = (BannerItem) var2.next();
            ImageView imageView = new ImageView(this.getContext());
            views.add(imageView);
        }

        this.mAdapter = new BannerAdapter(views, this.mBannerItems, this.mParams.cycleMode, this.mListener);
        this.mViewPager.setAdapter(this.mAdapter);
        this.mViewPager.setCurrentItem(this.mParams.initPosition);
    }

    public void startAutoPlay() {
        this.mParams.autoPlayMode = true;
        this.startAutoPlayInternal();
    }

    private void startAutoPlayInternal() {
        this.mAutoPlayModeSubscription = Observable.interval(this.mParams.intervalTime, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Long>() {
            public void call(Long aLong) {
                if (BannerView.this.mAdapter != null && BannerView.this.mParams.autoPlayMode) {
                    int position = BannerView.this.mViewPager.getCurrentItem();
                    ++position;
                    position %= BannerView.this.mAdapter.getCount();
                    BannerView.this.mViewPager.setCurrentItem(position, true);
                }

            }
        });
        Log.d("BannerView", "startAutoPlayInternal");
    }

    public void stopAutoPlay() {
        this.mParams.autoPlayMode = false;
        this.stopAutoPlayInternal();
    }

    private void stopAutoPlayInternal() {
        if (this.mAutoPlayModeSubscription != null && !this.mAutoPlayModeSubscription.isUnsubscribed()) {
            this.mAutoPlayModeSubscription.unsubscribe();
            this.mAutoPlayModeSubscription = null;
            Log.d("BannerView", "stopAutoPlayInternal");
        }

    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int realPosition;
        if (this.mParams.cycleMode) {
            realPosition = PositionUtil.getRealPosition(position, this.mAdapter.getCount());
        } else {
            realPosition = position;
        }

        if (this.mIndicator != null) {
            this.mIndicator.onPageScrolled(realPosition, positionOffset, positionOffsetPixels, this.mBannerItems.get(position));
        }

    }

    public void onPageSelected(int position) {
        int realPosition;
        if (this.mParams.cycleMode) {
            realPosition = PositionUtil.getRealPosition(position, this.mAdapter.getCount());
        } else {
            realPosition = position;
        }

        if (this.mIndicator != null) {
            this.mIndicator.onPageSelected(realPosition, this.mBannerItems.get(position));
        }

    }

    public void onPageScrollStateChanged(int state) {
        if (state == 0 && this.mParams.cycleMode) {
            if (this.mViewPager.getCurrentItem() == this.mViewPager.getAdapter().getCount() - 1) {
                this.mViewPager.setCurrentItem(1, false);
            } else if (this.mViewPager.getCurrentItem() == 0) {
                this.mViewPager.setCurrentItem(this.mViewPager.getAdapter().getCount() - 2, false);
            }
        }

        if (this.mIndicator != null) {
            this.mIndicator.onPageScrollStateChanged(state);
        }

    }

    private void fillCycleModeData(LinkedList<BannerItem> bannerItems) {
        if (bannerItems.size() > 0) {
            BannerItem firstItem = (BannerItem) bannerItems.getFirst();
            BannerItem lastItem = (BannerItem) bannerItems.getLast();
            bannerItems.addFirst(lastItem);
            bannerItems.addLast(firstItem);
        }

    }

    public void setIntervalTime(long time) {
        this.mParams.intervalTime = time;
    }

    public void setCycleMode(boolean cycleMode) {
        this.mParams.cycleMode = cycleMode;
    }

    public void setAutoPlayMode(boolean autoPlayMode) {
        this.mParams.autoPlayMode = autoPlayMode;
    }

    public void setIndicator(Indicator indicator) {
        this.mIndicator = indicator;
        if (this.hasIndicator()) {
            this.removeViewAt(1);
        }

        if (this.mIndicator != null) {
            View view = this.mIndicator.getLayout(this.getContext(), this);
            android.view.ViewGroup.LayoutParams viewLayoutParams = view.getLayoutParams();
            if (viewLayoutParams != null) {
                this.addView(view, new LayoutParams(viewLayoutParams.width, viewLayoutParams.height, this.mIndicator.getGravity()));
            }
        }

    }

    public void setOnBannerItemClickListener(OnBannerItemClickListener listener) {
        this.mListener = listener;
    }

    private boolean hasIndicator() {
        return this.getChildCount() >= 2;
    }

    private static class Params {
        long    intervalTime;
        int     initPosition;
        boolean autoPlayMode;
        boolean cycleMode;

        private Params() {
            this.intervalTime = 3000L;
            this.initPosition = 1;
            this.autoPlayMode = true;
            this.cycleMode = true;
        }
    }
}
