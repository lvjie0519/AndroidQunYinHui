package com.example.androidqunyinhui.android.banner.indicator.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.view.animation.AccelerateDecelerateInterpolator;

import java.util.Iterator;

/**
 * Created by caijj on 2017/8/16.
 */
public class WormAnimation extends AbsAnimation<AnimatorSet> {
    int fromValue;
    int toValue;
    int radius;
    boolean isRightSide;
    int rectLeftX;
    int rectRightX;

    public WormAnimation(@NonNull ValueAnimation.UpdateListener listener) {
        super(listener);
    }

    @NonNull
    public AnimatorSet createAnimator() {
        AnimatorSet animator = new AnimatorSet();
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        return animator;
    }

    public WormAnimation with(int fromValue, int toValue, int radius, boolean isRightSide) {
        if(this.hasChanges(fromValue, toValue, radius, isRightSide)) {
            this.animator = this.createAnimator();
            this.fromValue = fromValue;
            this.toValue = toValue;
            this.radius = radius;
            this.isRightSide = isRightSide;
            this.rectLeftX = fromValue - radius;
            this.rectRightX = fromValue + radius;
            AnimationValues values = this.createAnimationValues(isRightSide);
            long duration = this.animationDuration / 2L;
            ValueAnimator straightAnimator = this.createWormAnimator(values.fromX, values.toX, duration, false);
            ValueAnimator reverseAnimator = this.createWormAnimator(values.reverseFromX, values.reverseToX, duration, true);
            ((AnimatorSet)this.animator).playSequentially(new Animator[]{straightAnimator, reverseAnimator});
        }

        return this;
    }

    public WormAnimation progress(float progress) {
        if(this.animator != null) {
            long playTimeLeft = (long)(progress * (float)this.animationDuration);

            long currPlayTime;
            for(Iterator var4 = ((AnimatorSet)this.animator).getChildAnimations().iterator(); var4.hasNext(); playTimeLeft -= currPlayTime) {
                Animator anim = (Animator)var4.next();
                ValueAnimator animator = (ValueAnimator)anim;
                long animDuration = animator.getDuration();
                if(playTimeLeft < 0L) {
                    playTimeLeft = 0L;
                }

                currPlayTime = playTimeLeft;
                if(playTimeLeft >= animDuration) {
                    currPlayTime = animDuration;
                }

                animator.setCurrentPlayTime(currPlayTime);
            }
        }

        return this;
    }

    ValueAnimator createWormAnimator(int fromX, int toX, long duration, final boolean isReverse) {
        ValueAnimator anim = ValueAnimator.ofInt(new int[]{fromX, toX});
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.setDuration(duration);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = ((Integer)animation.getAnimatedValue()).intValue();
                if(!isReverse) {
                    if(WormAnimation.this.isRightSide) {
                        WormAnimation.this.rectRightX = value;
                    } else {
                        WormAnimation.this.rectLeftX = value;
                    }
                } else if(WormAnimation.this.isRightSide) {
                    WormAnimation.this.rectLeftX = value;
                } else {
                    WormAnimation.this.rectRightX = value;
                }

                WormAnimation.this.listener.onWormAnimationUpdated(WormAnimation.this.rectLeftX, WormAnimation.this.rectRightX);
            }
        });
        return anim;
    }

    boolean hasChanges(int fromValue, int toValue, int radius, boolean isRightSide) {
        return this.fromValue != fromValue?true:(this.toValue != toValue?true:(this.radius != radius?true:this.isRightSide != isRightSide));
    }

    @NonNull
    AnimationValues createAnimationValues(boolean isRightSide) {
        int fromX;
        int toX;
        int reverseFromX;
        int reverseToX;
        if(isRightSide) {
            fromX = this.fromValue + this.radius;
            toX = this.toValue + this.radius;
            reverseFromX = this.fromValue - this.radius;
            reverseToX = this.toValue - this.radius;
        } else {
            fromX = this.fromValue - this.radius;
            toX = this.toValue - this.radius;
            reverseFromX = this.fromValue + this.radius;
            reverseToX = this.toValue + this.radius;
        }

        return new AnimationValues(fromX, toX, reverseFromX, reverseToX);
    }

    class AnimationValues {
        final int fromX;
        final int toX;
        final int reverseFromX;
        final int reverseToX;

        AnimationValues(int fromX, int toX, int reverseFromX, int reverseToX) {
            this.fromX = fromX;
            this.toX = toX;
            this.reverseFromX = reverseFromX;
            this.reverseToX = reverseToX;
        }
    }
}