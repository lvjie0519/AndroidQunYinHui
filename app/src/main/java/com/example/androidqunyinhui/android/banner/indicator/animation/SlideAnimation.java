package com.example.androidqunyinhui.android.banner.indicator.animation;

import android.animation.IntEvaluator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by caijj on 2017/8/16.
 */
public class SlideAnimation extends AbsAnimation<ValueAnimator> {
    private static final String ANIMATION_X_COORDINATE = "ANIMATION_X_COORDINATE";
    private static final int    ANIMATION_DURATION     = 350;
    private int xStartCoordinate;
    private int xEndCoordinate;

    public SlideAnimation(@NonNull ValueAnimation.UpdateListener listener) {
        super(listener);
    }

    @NonNull
    public ValueAnimator createAnimator() {
        ValueAnimator animator = new ValueAnimator();
        animator.setDuration(350L);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                SlideAnimation.this.onAnimateUpdated(animation);
            }
        });
        return animator;
    }

    public SlideAnimation progress(float progress) {
        if (this.animator != null) {
            long playTime = (long) (progress * (float) this.animationDuration);
            ((ValueAnimator) this.animator).setCurrentPlayTime(playTime);
        }

        return this;
    }

    @NonNull
    public SlideAnimation with(int startValue, int endValue) {
        if (this.animator != null && this.hasChanges(startValue, endValue)) {
            this.xStartCoordinate = startValue;
            this.xEndCoordinate = endValue;
            PropertyValuesHolder holder = this.createColorPropertyHolder();
            ((ValueAnimator) this.animator).setValues(new PropertyValuesHolder[]{holder});
        }

        return this;
    }

    private PropertyValuesHolder createColorPropertyHolder() {
        PropertyValuesHolder holder = PropertyValuesHolder.ofInt("ANIMATION_X_COORDINATE", new int[]{this.xStartCoordinate, this.xEndCoordinate});
        holder.setEvaluator(new IntEvaluator());
        return holder;
    }

    private void onAnimateUpdated(@NonNull ValueAnimator animation) {
        int xCoordinate = ((Integer) animation.getAnimatedValue("ANIMATION_X_COORDINATE")).intValue();
        if (this.listener != null) {
            this.listener.onSlideAnimationUpdated(xCoordinate);
        }

    }

    private boolean hasChanges(int startValue, int endValue) {
        return this.xStartCoordinate != startValue ? true : this.xEndCoordinate != endValue;
    }
}