package com.example.androidqunyinhui.android.banner.indicator.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.support.annotation.NonNull;

/**
 * Created by caijj on 2017/8/16.
 */
public abstract class AbsAnimation<T extends Animator> {
    public static final int DEFAULT_ANIMATION_TIME = 350;
    protected long animationDuration = 350L;
    protected ValueAnimation.UpdateListener listener;
    protected T                             animator;

    public AbsAnimation(@NonNull ValueAnimation.UpdateListener listener) {
        this.listener = listener;
        this.animator = this.createAnimator();
    }

    @NonNull
    public abstract T createAnimator();

    public abstract AbsAnimation progress(float var1);

    public AbsAnimation duration(long duration) {
        this.animationDuration = duration;
        if(this.animator instanceof AnimatorSet) {
            long singleDuration = this.animationDuration / 2L;
            this.animator.setDuration(singleDuration);
        } else {
            this.animator.setDuration(this.animationDuration);
        }

        return this;
    }

    public void start() {
        if(this.animator != null && !this.animator.isRunning()) {
            this.animator.start();
        }

    }

    public void end() {
        if(this.animator != null && this.animator.isStarted()) {
            this.animator.end();
        }

    }
}
