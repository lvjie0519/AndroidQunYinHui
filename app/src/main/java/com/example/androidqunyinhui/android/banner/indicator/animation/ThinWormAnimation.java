package com.example.androidqunyinhui.android.banner.indicator.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by caijj on 2017/8/16.
 */
public class ThinWormAnimation extends WormAnimation {
    private static final float PERCENTAGE_SIZE_DURATION_DELAY = 0.7F;
    private static final float PERCENTAGE_REVERSE_HEIGHT_DELAY = 0.65F;
    private static final float PERCENTAGE_HEIGHT_DURATION = 0.25F;
    private int height;

    public ThinWormAnimation(@NonNull ValueAnimation.UpdateListener listener) {
        super(listener);
    }

    public ThinWormAnimation duration(long duration) {
        super.duration(duration);
        return this;
    }

    public WormAnimation with(int fromValue, int toValue, int radius, boolean isRightSide) {
        if(this.hasChanges(fromValue, toValue, radius, isRightSide)) {
            this.animator = this.createAnimator();
            this.fromValue = fromValue;
            this.toValue = toValue;
            this.radius = radius;
            this.height = radius * 2;
            this.isRightSide = isRightSide;
            this.rectLeftX = fromValue - radius;
            this.rectRightX = fromValue + radius;
            long straightSizeDuration = (long)((float)this.animationDuration * 0.7F);
            long reverseSizeDuration = this.animationDuration;
            long straightHeightDelay = 0L;
            long reverseHeightDelay = (long)((float)this.animationDuration * 0.65F);
            AnimationValues values = this.createAnimationValues(isRightSide);
            ValueAnimator straightAnimator = this.createWormAnimator(values.fromX, values.toX, straightSizeDuration, false);
            ValueAnimator straightHeightAnimator = this.createHeightAnimator(this.height, this.height / 2, straightHeightDelay);
            ValueAnimator reverseAnimator = this.createWormAnimator(values.reverseFromX, values.reverseToX, reverseSizeDuration, true);
            ValueAnimator reverseHeightAnimator = this.createHeightAnimator(this.height / 2, this.height, reverseHeightDelay);
            ((AnimatorSet)this.animator).playTogether(new Animator[]{straightAnimator, reverseAnimator, straightHeightAnimator, reverseHeightAnimator});
        }

        return this;
    }

    private ValueAnimator createHeightAnimator(int fromHeight, int toHeight, long startDelay) {
        ValueAnimator anim = ValueAnimator.ofInt(new int[]{fromHeight, toHeight});
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.setDuration((long)((float)this.animationDuration * 0.25F));
        anim.setStartDelay(startDelay);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                ThinWormAnimation.this.height = ((Integer)animation.getAnimatedValue()).intValue();
                ThinWormAnimation.this.listener.onThinWormAnimationUpdated(ThinWormAnimation.this.rectLeftX, ThinWormAnimation.this.rectRightX, ThinWormAnimation.this.height);
            }
        });
        return anim;
    }

    public ThinWormAnimation progress(float progress) {
        if(this.animator != null) {
            long duration = (long)(progress * (float)this.animationDuration);
            int size = ((AnimatorSet)this.animator).getChildAnimations().size();
            long minus = (long)((float)this.animationDuration * 0.65F);

            for(int i = 0; i < size; ++i) {
                ValueAnimator anim = (ValueAnimator)((AnimatorSet)this.animator).getChildAnimations().get(i);
                if(i == 3) {
                    if(duration < minus) {
                        break;
                    }

                    duration -= minus;
                }

                long currPlayTime = duration;
                if(duration >= anim.getDuration()) {
                    currPlayTime = anim.getDuration();
                }

                anim.setCurrentPlayTime(currPlayTime);
            }
        }

        return this;
    }
}
