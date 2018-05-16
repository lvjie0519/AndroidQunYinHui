package com.example.androidqunyinhui.android.banner.indicator.animation;

import android.animation.ArgbEvaluator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by caijj on 2017/8/16.
 */
public class ColorAnimation extends AbsAnimation<ValueAnimator> {
    public static final  String DEFAULT_UNSELECTED_COLOR = "#33ffffff";
    public static final  String DEFAULT_SELECTED_COLOR   = "#ffffff";
    private static final String ANIMATION_COLOR_REVERSE  = "ANIMATION_COLOR_REVERSE";
    private static final String ANIMATION_COLOR          = "ANIMATION_COLOR";
    private static final int    ANIMATION_DURATION       = 350;
    protected int startColor;
    protected int endColor;

    public ColorAnimation(@NonNull ValueAnimation.UpdateListener listener) {
        super(listener);
    }

    @NonNull
    public ValueAnimator createAnimator() {
        ValueAnimator animator = new ValueAnimator();
        animator.setDuration(350L);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                ColorAnimation.this.onAnimateUpdated(animation);
            }
        });
        return animator;
    }

    public ColorAnimation progress(float progress) {
        if(this.animator != null) {
            long playTime = (long)(progress * (float)this.animationDuration);
            ((ValueAnimator)this.animator).setCurrentPlayTime(playTime);
        }

        return this;
    }

    @NonNull
    public ColorAnimation with(int colorStartValue, int colorEndValue) {
        if(this.animator != null && this.hasChanges(colorStartValue, colorEndValue)) {
            this.startColor = colorStartValue;
            this.endColor = colorEndValue;
            PropertyValuesHolder colorHolder = this.createColorPropertyHolder(false);
            PropertyValuesHolder reverseColorHolder = this.createColorPropertyHolder(true);
            ((ValueAnimator)this.animator).setValues(new PropertyValuesHolder[]{colorHolder, reverseColorHolder});
        }

        return this;
    }

    protected PropertyValuesHolder createColorPropertyHolder(boolean isReverse) {
        String propertyName;
        int startColorValue;
        int endColorValue;
        if(isReverse) {
            propertyName = "ANIMATION_COLOR_REVERSE";
            startColorValue = this.endColor;
            endColorValue = this.startColor;
        } else {
            propertyName = "ANIMATION_COLOR";
            startColorValue = this.startColor;
            endColorValue = this.endColor;
        }

        PropertyValuesHolder holder = PropertyValuesHolder.ofInt(propertyName, new int[]{startColorValue, endColorValue});
        holder.setEvaluator(new ArgbEvaluator());
        return holder;
    }

    private boolean hasChanges(int colorStartValue, int colorEndValue) {
        return this.startColor != colorStartValue?true:this.endColor != colorEndValue;
    }

    private void onAnimateUpdated(@NonNull ValueAnimator animation) {
        int color = ((Integer)animation.getAnimatedValue("ANIMATION_COLOR")).intValue();
        int colorReverse = ((Integer)animation.getAnimatedValue("ANIMATION_COLOR_REVERSE")).intValue();
        if(this.listener != null) {
            this.listener.onColorAnimationUpdated(color, colorReverse);
        }

    }
}
