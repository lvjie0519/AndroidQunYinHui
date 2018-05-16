package com.example.androidqunyinhui.android.banner.indicator.animation;

import android.animation.IntEvaluator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by caijj on 2017/8/16.
 */
public class ScaleAnimation extends ColorAnimation {
    public static final  float  DEFAULT_SCALE_FACTOR    = 0.7F;
    public static final  float  MIN_SCALE_FACTOR        = 0.3F;
    public static final  float  MAX_SCALE_FACTOR        = 1.0F;
    private static final String ANIMATION_COLOR_REVERSE = "ANIMATION_COLOR_REVERSE";
    private static final String ANIMATION_COLOR         = "ANIMATION_COLOR";
    private static final String ANIMATION_SCALE_REVERSE = "ANIMATION_SCALE_REVERSE";
    private static final String ANIMATION_SCALE         = "ANIMATION_SCALE";
    private static final int    ANIMATION_DURATION      = 350;
    private int   radiusPx;
    private float scaleFactor;

    public ScaleAnimation(@NonNull ValueAnimation.UpdateListener listener) {
        super(listener);
    }

    @NonNull
    public ValueAnimator createAnimator() {
        ValueAnimator animator = new ValueAnimator();
        animator.setDuration(350L);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                ScaleAnimation.this.onAnimateUpdated(animation);
            }
        });
        return animator;
    }

    @NonNull
    public ScaleAnimation with(int colorStartValue, int colorEndValue, int radiusValue, float scaleFactorValue) {
        if (this.animator != null && this.hasChanges(colorStartValue, colorEndValue, radiusValue, scaleFactorValue)) {
            this.startColor = colorStartValue;
            this.endColor = colorEndValue;
            this.radiusPx = radiusValue;
            this.scaleFactor = scaleFactorValue;
            PropertyValuesHolder colorHolder = this.createColorPropertyHolder(false);
            PropertyValuesHolder reverseColorHolder = this.createColorPropertyHolder(true);
            PropertyValuesHolder scaleHolder = this.createScalePropertyHolder(false);
            PropertyValuesHolder scaleReverseHolder = this.createScalePropertyHolder(true);
            ((ValueAnimator) this.animator).setValues(new PropertyValuesHolder[]{colorHolder, reverseColorHolder, scaleHolder, scaleReverseHolder});
        }

        return this;
    }

    private void onAnimateUpdated(@NonNull ValueAnimator animation) {
        int color = ((Integer) animation.getAnimatedValue("ANIMATION_COLOR")).intValue();
        int colorReverse = ((Integer) animation.getAnimatedValue("ANIMATION_COLOR_REVERSE")).intValue();
        int radius = ((Integer) animation.getAnimatedValue("ANIMATION_SCALE")).intValue();
        int radiusReverse = ((Integer) animation.getAnimatedValue("ANIMATION_SCALE_REVERSE")).intValue();
        if (this.listener != null) {
            this.listener.onScaleAnimationUpdated(color, colorReverse, radius, radiusReverse);
        }

    }

    @NonNull
    private PropertyValuesHolder createScalePropertyHolder(boolean isReverse) {
        String propertyName;
        int startRadiusValue;
        int endRadiusValue;
        if (isReverse) {
            propertyName = "ANIMATION_SCALE_REVERSE";
            startRadiusValue = this.radiusPx;
            endRadiusValue = (int) ((float) this.radiusPx * this.scaleFactor);
        } else {
            propertyName = "ANIMATION_SCALE";
            startRadiusValue = (int) ((float) this.radiusPx * this.scaleFactor);
            endRadiusValue = this.radiusPx;
        }

        PropertyValuesHolder holder = PropertyValuesHolder.ofInt(propertyName, new int[]{startRadiusValue, endRadiusValue});
        holder.setEvaluator(new IntEvaluator());
        return holder;
    }

    private boolean hasChanges(int colorStartValue, int colorEndValue, int radiusValue, float scaleFactorValue) {
        return this.startColor != colorStartValue ? true : (this.endColor != colorEndValue ? true : (this.radiusPx != radiusValue ? true : this.scaleFactor != scaleFactorValue));
    }
}