package com.example.androidqunyinhui.android.banner.indicator.animation;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by caijj on 2017/8/16.
 */
public class ValueAnimation {
    private ColorAnimation                                                           colorAnimation;
    private ScaleAnimation                                                           scaleAnimation;
    private WormAnimation                                                            wormAnimation;
    private SlideAnimation                                                           slideAnimation;
    private FillAnimation                                                            fillAnimation;
    private ThinWormAnimation                                                        thinWormAnimation;
    private UpdateListener updateListener;

    public ValueAnimation(@Nullable UpdateListener listener) {
        this.updateListener = listener;
    }

    @NonNull
    public ColorAnimation color() {
        if(this.colorAnimation == null) {
            this.colorAnimation = new ColorAnimation(this.updateListener);
        }

        return this.colorAnimation;
    }

    @NonNull
    public ScaleAnimation scale() {
        if(this.scaleAnimation == null) {
            this.scaleAnimation = new ScaleAnimation(this.updateListener);
        }

        return this.scaleAnimation;
    }

    @NonNull
    public WormAnimation worm() {
        if(this.wormAnimation == null) {
            this.wormAnimation = new WormAnimation(this.updateListener);
        }

        return this.wormAnimation;
    }

    @NonNull
    public SlideAnimation slide() {
        if(this.slideAnimation == null) {
            this.slideAnimation = new SlideAnimation(this.updateListener);
        }

        return this.slideAnimation;
    }

    @NonNull
    public FillAnimation fill() {
        if(this.fillAnimation == null) {
            this.fillAnimation = new FillAnimation(this.updateListener);
        }

        return this.fillAnimation;
    }

    @NonNull
    public ThinWormAnimation thinWorm() {
        if(this.thinWormAnimation == null) {
            this.thinWormAnimation = new ThinWormAnimation(this.updateListener);
        }

        return this.thinWormAnimation;
    }

    public interface UpdateListener {
        void onColorAnimationUpdated(int var1, int var2);

        void onScaleAnimationUpdated(int var1, int var2, int var3, int var4);

        void onSlideAnimationUpdated(int var1);

        void onWormAnimationUpdated(int var1, int var2);

        void onFillAnimationUpdated(int var1, int var2, int var3, int var4, int var5, int var6);

        void onThinWormAnimationUpdated(int var1, int var2, int var3);
    }
}