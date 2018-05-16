package com.example.androidqunyinhui.android.banner.indicator;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;

import com.example.androidqunyinhui.R;
import com.example.androidqunyinhui.android.banner.indicator.animation.AbsAnimation;
import com.example.androidqunyinhui.android.banner.indicator.animation.AnimationType;
import com.example.androidqunyinhui.android.banner.indicator.animation.ValueAnimation;
import com.example.androidqunyinhui.android.banner.util.DensityUtils;


/**
 * Created by caijj on 2017/8/16.
 */
public class PageIndicatorView extends View implements ViewPager.OnPageChangeListener {
    private static final int DEFAULT_CIRCLES_COUNT = 3;
    private static final int COUNT_NOT_SET         = -1;
    private static final int DEFAULT_RADIUS_DP     = 6;
    private static final int DEFAULT_PADDING_DP    = 8;
    private int             radiusPx;
    private int             paddingPx;
    private int             strokePx;
    private int             count;
    private boolean         isCountSet;
    private int             unselectedColor;
    private int             selectedColor;
    private int             frameColor;
    private int             frameColorReverse;
    private int             frameRadiusPx;
    private int             frameRadiusReversePx;
    private float           scaleFactor;
    private int             frameStrokePx;
    private int             frameStrokeReversePx;
    private int             frameLeftX;
    private int             frameRightX;
    private int             frameXCoordinate;
    private int             frameHeight;
    private int             selectedPosition;
    private int             selectingPosition;
    private int             lastSelectedPosition;
    private boolean         isFrameValuesSet;
    private boolean         interactiveAnimation;
    private long            animationDuration;
    private DataSetObserver setObserver;
    private boolean         dynamicCount;
    private Paint fillPaint   = new Paint();
    private Paint strokePaint = new Paint();
    private RectF rect        = new RectF();
    private AnimationType  animationType;
    private ValueAnimation animation;
    private ViewPager      viewPager;
    private int            viewPagerId;

    public PageIndicatorView(Context context) {
        super(context);
        this.animationType = AnimationType.NONE;
        this.init((AttributeSet) null);
    }

    public PageIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.animationType = AnimationType.NONE;
        this.init(attrs);
    }

    public PageIndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.animationType = AnimationType.NONE;
        this.init(attrs);
    }

    @TargetApi(21)
    public PageIndicatorView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.animationType = AnimationType.NONE;
        this.init(attrs);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.findViewPager();
    }

    protected void onDetachedFromWindow() {
        this.unRegisterSetObserver();
        super.onDetachedFromWindow();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int circleDiameterPx = this.radiusPx * 2;
        int desiredHeight = circleDiameterPx + this.strokePx;
        int desiredWidth = 0;
        int width;
        int height;
        if (this.count != 0) {
            width = circleDiameterPx * this.count;
            height = this.strokePx * 2 * this.count;
            int paddingSum = this.paddingPx * (this.count - 1);
            desiredWidth = width + height + paddingSum;
        }

        if (widthMode == 1073741824) {
            width = widthSize;
        } else if (widthMode == -2147483648) {
            width = Math.min(desiredWidth, widthSize);
        } else {
            width = desiredWidth;
        }

        if (heightMode == 1073741824) {
            height = heightSize;
        } else if (heightMode == -2147483648) {
            height = Math.min(desiredHeight, heightSize);
        } else {
            height = desiredHeight;
        }

        if (width < 0) {
            width = 0;
        }

        if (height < 0) {
            height = 0;
        }

        this.setMeasuredDimension(width, height);
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        this.setFrameValues();
    }

    protected void onDraw(Canvas canvas) {
        this.drawIndicatorView(canvas);
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (this.interactiveAnimation) {
            this.onPageScroll(position, positionOffset);
        }

    }

    public void onPageSelected(int position) {
        if (!this.interactiveAnimation || this.animationType == AnimationType.NONE) {
            this.setSelection(position);
        }

    }

    public void onPageScrollStateChanged(int state) {
    }

    public void setCount(int count) {
        if (this.count != count) {
            this.count = count;
            this.isCountSet = true;
            this.requestLayout();
        }

    }

    public int getCount() {
        return this.count;
    }

    public void setDynamicCount(boolean dynamicCount) {
        this.dynamicCount = dynamicCount;
        if (dynamicCount) {
            this.registerSetObserver();
        } else {
            this.unRegisterSetObserver();
        }

    }

    public void setRadius(int radiusDp) {
        if (radiusDp < 0) {
            radiusDp = 0;
        }

        this.radiusPx = DensityUtils.dpToPx(radiusDp);
        this.invalidate();
    }

    public void setRadius(float radiusPx) {
        if (radiusPx < 0.0F) {
            radiusPx = 0.0F;
        }

        this.radiusPx = (int) radiusPx;
        this.invalidate();
    }

    public int getRadius() {
        return this.radiusPx;
    }

    public void setPadding(int paddingDp) {
        if (paddingDp < 0) {
            paddingDp = 0;
        }

        this.paddingPx = DensityUtils.dpToPx(paddingDp);
        this.invalidate();
    }

    public void setPadding(float paddingPx) {
        if (paddingPx < 0.0F) {
            paddingPx = 0.0F;
        }

        this.paddingPx = (int) paddingPx;
        this.invalidate();
    }

    public int getPadding() {
        return this.paddingPx;
    }

    public void setScaleFactor(float factor) {
        if (factor > 1.0F) {
            factor = 1.0F;
        } else if (factor < 0.3F) {
            factor = 0.3F;
        }

        this.scaleFactor = factor;
    }

    public float getScaleFactor() {
        return this.scaleFactor;
    }

    public void setStrokeWidth(float strokePx) {
        if (strokePx < 0.0F) {
            strokePx = 0.0F;
        } else if (strokePx > (float) this.radiusPx) {
            strokePx = (float) this.radiusPx;
        }

        this.strokePx = (int) strokePx;
        this.invalidate();
    }

    public void setStrokeWidth(int strokeDp) {
        int strokePx = DensityUtils.dpToPx(strokeDp);
        if (strokePx < 0) {
            strokePx = 0;
        } else if (strokePx > this.radiusPx) {
            strokePx = this.radiusPx;
        }

        this.strokePx = strokePx;
        this.invalidate();
    }

    public int getStrokeWidth() {
        return this.strokePx;
    }

    public void setUnselectedColor(int color) {
        this.unselectedColor = color;
        this.invalidate();
    }

    public int getUnselectedColor() {
        return this.unselectedColor;
    }

    public void setSelectedColor(int color) {
        this.selectedColor = color;
        this.invalidate();
    }

    public int getSelectedColor() {
        return this.selectedColor;
    }

    public void setAnimationDuration(long duration) {
        this.animationDuration = duration;
    }

    public long getAnimationDuration() {
        return this.animationDuration;
    }

    public void setAnimationType(@Nullable AnimationType type) {
        if (type != null) {
            this.animationType = type;
        } else {
            this.animationType = AnimationType.NONE;
        }

    }

    public void setInteractiveAnimation(boolean isInteractive) {
        this.interactiveAnimation = isInteractive;
    }

    public void setProgress(int selectingPosition, float progress) {
        if (this.interactiveAnimation) {
            if (selectingPosition < 0) {
                selectingPosition = 0;
            } else if (selectingPosition > this.count - 1) {
                selectingPosition = this.count - 1;
            }

            if (progress < 0.0F) {
                progress = 0.0F;
            } else if (progress > 1.0F) {
                progress = 1.0F;
            }

            this.selectingPosition = selectingPosition;
            AbsAnimation animator = this.getSelectedAnimation();
            if (animator != null) {
                animator.progress(progress);
            }
        }

    }

    public void setSelection(int position) {
        if (position < 0) {
            position = 0;
        } else if (position > this.count - 1) {
            position = this.count - 1;
        }

        this.lastSelectedPosition = this.selectedPosition;
        this.selectedPosition = position;
        switch (this.animationType.ordinal()) {
            case 0:
            case 1:
                this.invalidate();
                break;
            case 2:
                this.startColorAnimation();
                break;
            case 3:
                this.startScaleAnimation();
                break;
            case 4:
                this.startWormAnimation();
                break;
            case 5:
                this.startFillAnimation();
                break;
            case 6:
                this.startSlideAnimation();
                break;
            case 7:
                this.startThinWormAnimation();
        }

    }

    public int getSelection() {
        return this.selectedPosition;
    }

    public void setViewPager(@Nullable ViewPager pager) {
        this.releaseViewPager();
        if (pager != null) {
            this.viewPager = pager;
            this.viewPager.addOnPageChangeListener(this);
            this.setDynamicCount(this.dynamicCount);
            if (!this.isCountSet) {
                this.setCount(this.getViewPagerCount());
            }
        }

    }

    public void releaseViewPager() {
        if (this.viewPager != null) {
            this.viewPager.removeOnPageChangeListener(this);
            this.viewPager = null;
        }

    }

    private void onPageScroll(int position, float positionOffset) {
        Pair progressPair = this.getProgress(position, positionOffset);
        int selectingPosition = ((Integer) progressPair.first).intValue();
        float selectingProgress = ((Float) progressPair.second).floatValue();
        if (selectingProgress == 1.0F) {
            this.lastSelectedPosition = this.selectedPosition;
            this.selectedPosition = selectingPosition;
        }

        this.setProgress(selectingPosition, selectingProgress);
    }

    private void drawIndicatorView(@NonNull Canvas canvas) {
        int y = this.getHeight() / 2;

        for (int i = 0; i < this.count; ++i) {
            int x = this.getXCoordinate(i);
            this.drawCircle(canvas, i, x, y);
        }

    }

    private void drawCircle(@NonNull Canvas canvas, int position, int x, int y) {
        boolean selectedItem = !this.interactiveAnimation && (position == this.selectedPosition || position == this.lastSelectedPosition);
        boolean selectingItem = this.interactiveAnimation && (position == this.selectingPosition || position == this.selectedPosition);
        boolean isSelectedItem = selectedItem | selectingItem;
        if (isSelectedItem) {
            this.drawWithAnimationEffect(canvas, position, x, y);
        } else {
            this.drawWithNoEffect(canvas, position, x, y);
        }

    }

    private void drawWithAnimationEffect(@NonNull Canvas canvas, int position, int x, int y) {
        switch (this.animationType.ordinal()) {
            case 0:
            case 1:
                this.drawWithNoEffect(canvas, position, x, y);
                break;
            case 2:
                this.drawWithColorAnimation(canvas, position, x, y);
                break;
            case 3:
                this.drawWithScaleAnimation(canvas, position, x, y);
                break;
            case 4:
                this.drawWithWormAnimation(canvas, x, y);
                break;
            case 5:
                this.drawWithFillAnimation(canvas, position, x, y);
                break;
            case 6:
                this.drawWithSlideAnimation(canvas, position, x, y);
                break;
            case 7:
                this.drawWithThinWormAnimation(canvas, x, y);
        }

    }

    private void drawWithNoEffect(@NonNull Canvas canvas, int position, int x, int y) {
        float radius = (float) this.radiusPx;
        int color = this.unselectedColor;
        if (this.animationType == AnimationType.SCALE) {
            radius *= this.scaleFactor;
        }

        if (position == this.selectedPosition) {
            color = this.selectedColor;
        }

        Paint paint;
        if (this.animationType == AnimationType.FILL) {
            paint = this.strokePaint;
            paint.setStrokeWidth((float) this.strokePx);
        } else {
            paint = this.fillPaint;
        }

        paint.setColor(color);
        canvas.drawCircle((float) x, (float) y, radius, paint);
    }

    private void drawWithColorAnimation(@NonNull Canvas canvas, int position, int x, int y) {
        int color = this.unselectedColor;
        if (this.interactiveAnimation) {
            if (position == this.selectingPosition) {
                color = this.frameColor;
            } else if (position == this.selectedPosition) {
                color = this.frameColorReverse;
            }
        } else if (position == this.selectedPosition) {
            color = this.frameColor;
        } else if (position == this.lastSelectedPosition) {
            color = this.frameColorReverse;
        }

        this.fillPaint.setColor(color);
        canvas.drawCircle((float) x, (float) y, (float) this.radiusPx, this.fillPaint);
    }

    private void drawWithScaleAnimation(@NonNull Canvas canvas, int position, int x, int y) {
        int color = this.unselectedColor;
        int radius = this.radiusPx;
        if (this.interactiveAnimation) {
            if (position == this.selectingPosition) {
                radius = this.frameRadiusPx;
                color = this.frameColor;
            } else if (position == this.selectedPosition) {
                radius = this.frameRadiusReversePx;
                color = this.frameColorReverse;
            }
        } else if (position == this.selectedPosition) {
            radius = this.frameRadiusPx;
            color = this.frameColor;
        } else if (position == this.lastSelectedPosition) {
            radius = this.frameRadiusReversePx;
            color = this.frameColorReverse;
        }

        this.fillPaint.setColor(color);
        canvas.drawCircle((float) x, (float) y, (float) radius, this.fillPaint);
    }

    private void drawWithSlideAnimation(@NonNull Canvas canvas, int position, int x, int y) {
        this.fillPaint.setColor(this.unselectedColor);
        canvas.drawCircle((float) x, (float) y, (float) this.radiusPx, this.fillPaint);
        if (this.interactiveAnimation && (position == this.selectingPosition || position == this.selectedPosition)) {
            this.fillPaint.setColor(this.selectedColor);
            canvas.drawCircle((float) this.frameXCoordinate, (float) y, (float) this.radiusPx, this.fillPaint);
        } else if (!this.interactiveAnimation && (position == this.selectedPosition || position == this.lastSelectedPosition)) {
            this.fillPaint.setColor(this.selectedColor);
            canvas.drawCircle((float) this.frameXCoordinate, (float) y, (float) this.radiusPx, this.fillPaint);
        }

    }

    private void drawWithWormAnimation(@NonNull Canvas canvas, int x, int y) {
        int radius = this.radiusPx;
        int left = this.frameLeftX;
        int right = this.frameRightX;
        int top = y - radius;
        int bot = y + radius;
        this.rect.left = (float) left;
        this.rect.right = (float) right;
        this.rect.top = (float) top;
        this.rect.bottom = (float) bot;
        this.fillPaint.setColor(this.unselectedColor);
        canvas.drawCircle((float) x, (float) y, (float) radius, this.fillPaint);
        this.fillPaint.setColor(this.selectedColor);
        canvas.drawRoundRect(this.rect, (float) this.radiusPx, (float) this.radiusPx, this.fillPaint);
    }

    private void drawWithFillAnimation(@NonNull Canvas canvas, int position, int x, int y) {
        int color = this.unselectedColor;
        float radius = (float) this.radiusPx;
        int stroke = this.strokePx;
        if (this.interactiveAnimation) {
            if (position == this.selectingPosition) {
                color = this.frameColor;
                radius = (float) this.frameRadiusPx;
                stroke = this.frameStrokePx;
            } else if (position == this.selectedPosition) {
                color = this.frameColorReverse;
                radius = (float) this.frameRadiusReversePx;
                stroke = this.frameStrokeReversePx;
            }
        } else if (position == this.selectedPosition) {
            color = this.frameColor;
            radius = (float) this.frameRadiusPx;
            stroke = this.frameStrokePx;
        } else if (position == this.lastSelectedPosition) {
            color = this.frameColorReverse;
            radius = (float) this.frameRadiusReversePx;
            stroke = this.frameStrokeReversePx;
        }

        this.strokePaint.setColor(color);
        this.strokePaint.setStrokeWidth((float) this.strokePx);
        canvas.drawCircle((float) x, (float) y, (float) this.radiusPx, this.strokePaint);
        this.strokePaint.setStrokeWidth((float) stroke);
        canvas.drawCircle((float) x, (float) y, radius, this.strokePaint);
    }

    private void drawWithThinWormAnimation(@NonNull Canvas canvas, int x, int y) {
        int radius = this.radiusPx;
        int left = this.frameLeftX;
        int right = this.frameRightX;
        int top = y - this.frameHeight / 2;
        int bot = y + this.frameHeight / 2;
        this.rect.left = (float) left;
        this.rect.right = (float) right;
        this.rect.top = (float) top;
        this.rect.bottom = (float) bot;
        this.fillPaint.setColor(this.unselectedColor);
        canvas.drawCircle((float) x, (float) y, (float) radius, this.fillPaint);
        this.fillPaint.setColor(this.selectedColor);
        canvas.drawRoundRect(this.rect, (float) this.radiusPx, (float) this.radiusPx, this.fillPaint);
    }

    private void init(@Nullable AttributeSet attrs) {
        this.initAttributes(attrs);
        this.initAnimation();
        this.fillPaint.setStyle(Paint.Style.FILL);
        this.fillPaint.setAntiAlias(true);
        this.strokePaint.setStyle(Paint.Style.STROKE);
        this.strokePaint.setAntiAlias(true);
        this.strokePaint.setStrokeWidth((float) this.strokePx);
    }

    private void initAttributes(@Nullable AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = this.getContext().obtainStyledAttributes(attrs, R.styleable.slp_sdk_EbIndicator);
            this.initCountAttribute(typedArray);
            this.initColorAttribute(typedArray);
            this.initAnimationAttribute(typedArray);
            this.initSizeAttribute(typedArray);
            typedArray.recycle();
        }
    }

    private void initCountAttribute(@NonNull TypedArray typedArray) {
        boolean dynamicCount = typedArray.getBoolean(R.styleable.slp_sdk_EbIndicator_dynamicCount, false);
        this.setDynamicCount(dynamicCount);
        this.count = typedArray.getInt(R.styleable.slp_sdk_EbIndicator_piv_count, -1);
        if (this.count != -1) {
            this.isCountSet = true;
        } else {
            this.count = 3;
        }

        int position = typedArray.getInt(R.styleable.slp_sdk_EbIndicator_piv_select, 0);
        if (position < 0) {
            position = 0;
        } else if (this.count > 0 && position > this.count - 1) {
            position = this.count - 1;
        }

        this.selectedPosition = position;
        this.selectingPosition = position;
        this.viewPagerId = typedArray.getResourceId(R.styleable.slp_sdk_EbIndicator_piv_viewPager, 0);
    }

    private void initColorAttribute(@NonNull TypedArray typedArray) {
        this.unselectedColor = typedArray.getColor(R.styleable.slp_sdk_EbIndicator_piv_unselectedColor, Color.parseColor("#33ffffff"));
        this.selectedColor = typedArray.getColor(R.styleable.slp_sdk_EbIndicator_piv_selectedColor, Color.parseColor("#ffffff"));
    }

    private void initAnimationAttribute(@NonNull TypedArray typedArray) {
        this.animationDuration = (long) typedArray.getInt(R.styleable.slp_sdk_EbIndicator_piv_animationDuration, 350);
        this.interactiveAnimation = typedArray.getBoolean(R.styleable.slp_sdk_EbIndicator_piv_interactiveAnimation, false);
        int index = typedArray.getInt(R.styleable.slp_sdk_EbIndicator_piv_animationType, AnimationType.NONE.ordinal());
        this.animationType = this.getAnimationType(index);
    }

    private void initSizeAttribute(@NonNull TypedArray typedArray) {
        this.radiusPx = (int) typedArray.getDimension(R.styleable.slp_sdk_EbIndicator_piv_radius, (float) DensityUtils.dpToPx(6));
        this.paddingPx = (int) typedArray.getDimension(R.styleable.slp_sdk_EbIndicator_piv_padding, (float) DensityUtils.dpToPx(8));
        this.scaleFactor = typedArray.getFloat(R.styleable.slp_sdk_EbIndicator_piv_scaleFactor, 0.7F);
        if (this.scaleFactor < 0.3F) {
            this.scaleFactor = 0.3F;
        } else if (this.scaleFactor > 1.0F) {
            this.scaleFactor = 1.0F;
        }

        this.strokePx = (int) typedArray.getDimension(R.styleable.slp_sdk_EbIndicator_piv_strokeWidth, (float) DensityUtils.dpToPx(1));
        if (this.strokePx > this.radiusPx) {
            this.strokePx = this.radiusPx;
        }

        if (this.animationType != AnimationType.FILL) {
            this.strokePx = 0;
        }

    }

    private void initAnimation() {
        this.animation = new ValueAnimation(new ValueAnimation.UpdateListener() {
            public void onColorAnimationUpdated(int color, int colorReverse) {
                PageIndicatorView.this.frameColor = color;
                PageIndicatorView.this.frameColorReverse = colorReverse;
                PageIndicatorView.this.invalidate();
            }

            public void onScaleAnimationUpdated(int color, int colorReverse, int radius, int radiusReverse) {
                PageIndicatorView.this.frameColor = color;
                PageIndicatorView.this.frameColorReverse = colorReverse;
                PageIndicatorView.this.frameRadiusPx = radius;
                PageIndicatorView.this.frameRadiusReversePx = radiusReverse;
                PageIndicatorView.this.invalidate();
            }

            public void onSlideAnimationUpdated(int xCoordinate) {
                PageIndicatorView.this.frameXCoordinate = xCoordinate;
                PageIndicatorView.this.invalidate();
            }

            public void onWormAnimationUpdated(int leftX, int rightX) {
                PageIndicatorView.this.frameLeftX = leftX;
                PageIndicatorView.this.frameRightX = rightX;
                PageIndicatorView.this.invalidate();
            }

            public void onThinWormAnimationUpdated(int leftX, int rightX, int height) {
                PageIndicatorView.this.frameLeftX = leftX;
                PageIndicatorView.this.frameRightX = rightX;
                PageIndicatorView.this.frameHeight = height;
                PageIndicatorView.this.invalidate();
            }

            public void onFillAnimationUpdated(int color, int colorReverse, int radius, int radiusReverse, int stroke, int strokeReverse) {
                PageIndicatorView.this.frameColor = color;
                PageIndicatorView.this.frameColorReverse = colorReverse;
                PageIndicatorView.this.frameRadiusPx = radius;
                PageIndicatorView.this.frameRadiusReversePx = radiusReverse;
                PageIndicatorView.this.frameStrokePx = stroke;
                PageIndicatorView.this.frameStrokeReversePx = strokeReverse;
                PageIndicatorView.this.invalidate();
            }
        });
    }

    private AnimationType getAnimationType(int index) {
        switch (index) {
            case 0:
                return AnimationType.NONE;
            case 1:
                return AnimationType.COLOR;
            case 2:
                return AnimationType.SCALE;
            case 3:
                return AnimationType.WORM;
            case 4:
                return AnimationType.SLIDE;
            case 5:
                return AnimationType.FILL;
            case 6:
                return AnimationType.THIN_WORM;
            default:
                return AnimationType.NONE;
        }
    }

    private void setFrameValues() {
        if (!this.isFrameValuesSet) {
            this.frameColor = this.selectedColor;
            this.frameColorReverse = this.unselectedColor;
            this.frameRadiusPx = this.radiusPx;
            this.frameRadiusReversePx = this.radiusPx;
            int xCoordinate = this.getXCoordinate(this.selectedPosition);
            if (xCoordinate - this.radiusPx >= 0) {
                this.frameLeftX = xCoordinate - this.radiusPx;
                this.frameRightX = xCoordinate + this.radiusPx;
            } else {
                this.frameLeftX = xCoordinate;
                this.frameRightX = xCoordinate + this.radiusPx * 2;
            }

            this.frameXCoordinate = xCoordinate;
            this.frameStrokePx = this.radiusPx;
            this.frameStrokeReversePx = this.radiusPx / 2;
            if (this.animationType == AnimationType.FILL) {
                this.frameRadiusPx = this.radiusPx / 2;
                this.frameRadiusReversePx = this.radiusPx;
            }

            this.frameHeight = this.radiusPx * 2;
            this.isFrameValuesSet = true;
        }
    }

    private void startColorAnimation() {
        this.animation.color().end();
        this.animation.color().with(this.unselectedColor, this.selectedColor).duration(this.animationDuration).start();
    }

    private void startScaleAnimation() {
        this.animation.scale().end();
        this.animation.scale().with(this.unselectedColor, this.selectedColor, this.radiusPx, this.scaleFactor).duration(this.animationDuration).start();
    }

    private void startSlideAnimation() {
        int fromX = this.getXCoordinate(this.lastSelectedPosition);
        int toX = this.getXCoordinate(this.selectedPosition);
        this.animation.slide().end();
        this.animation.slide().with(fromX, toX).duration(this.animationDuration).start();
    }

    private void startWormAnimation() {
        int fromX = this.getXCoordinate(this.lastSelectedPosition);
        int toX = this.getXCoordinate(this.selectedPosition);
        boolean isRightSide = this.selectedPosition > this.lastSelectedPosition;
        this.animation.worm().end();
        this.animation.worm().with(fromX, toX, this.radiusPx, isRightSide).duration(this.animationDuration).start();
    }

    private void startFillAnimation() {
        this.animation.fill().end();
        this.animation.fill().with(this.unselectedColor, this.selectedColor, this.radiusPx, this.strokePx).duration(this.animationDuration).start();
    }

    private void startThinWormAnimation() {
        int fromX = this.getXCoordinate(this.lastSelectedPosition);
        int toX = this.getXCoordinate(this.selectedPosition);
        boolean isRightSide = this.selectedPosition > this.lastSelectedPosition;
        this.animation.thinWorm().end();
        this.animation.thinWorm().duration(this.animationDuration).with(fromX, toX, this.radiusPx, isRightSide).start();
    }

    @Nullable
    private AbsAnimation getSelectedAnimation() {
        switch (this.animationType.ordinal()) {
            case 2:
                return this.animation.color().with(this.unselectedColor, this.selectedColor);
            case 3:
                return this.animation.scale().with(this.unselectedColor, this.selectedColor, this.radiusPx, this.scaleFactor);
            case 4:
            case 6:
            case 7:
                int fromX = this.getXCoordinate(this.selectedPosition);
                int toX = this.getXCoordinate(this.selectingPosition);
                if (this.animationType == AnimationType.SLIDE) {
                    return this.animation.slide().with(fromX, toX);
                } else {
                    boolean isRightSide = this.selectingPosition > this.selectedPosition;
                    if (this.animationType == AnimationType.WORM) {
                        return this.animation.worm().with(fromX, toX, this.radiusPx, isRightSide);
                    } else if (this.animationType == AnimationType.THIN_WORM) {
                        return this.animation.thinWorm().with(fromX, toX, this.radiusPx, isRightSide);
                    }
                }
            default:
                return null;
            case 5:
                return this.animation.fill().with(this.unselectedColor, this.selectedColor, this.radiusPx, this.strokePx);
        }
    }

    private void registerSetObserver() {
        if (this.setObserver == null && this.viewPager != null && this.viewPager.getAdapter() != null) {
            this.setObserver = new DataSetObserver() {
                public void onChanged() {
                    super.onChanged();
                    if (PageIndicatorView.this.viewPager != null && PageIndicatorView.this.viewPager.getAdapter() != null) {
                        int count = PageIndicatorView.this.viewPager.getAdapter().getCount();
                        PageIndicatorView.this.setCount(count);
                    }

                }
            };
            this.viewPager.getAdapter().registerDataSetObserver(this.setObserver);
        }

    }

    private void unRegisterSetObserver() {
        if (this.setObserver != null && this.viewPager != null && this.viewPager.getAdapter() != null) {
            this.viewPager.getAdapter().unregisterDataSetObserver(this.setObserver);
            this.setObserver = null;
        }

    }

    private int getViewPagerCount() {
        return this.viewPager != null && this.viewPager.getAdapter() != null ? this.viewPager.getAdapter().getCount() : this.count;
    }

    private void findViewPager() {
        if (this.viewPagerId != 0) {
            Context context = this.getContext();
            if (context instanceof Activity) {
                Activity activity = (Activity) this.getContext();
                View view = activity.findViewById(this.viewPagerId);
                if (view != null && view instanceof ViewPager) {
                    this.setViewPager((ViewPager) view);
                }
            }

        }
    }

    private int getXCoordinate(int position) {
        int actualViewWidth = this.calculateActualViewWidth();
        int viewCenter = (this.getWidth() - actualViewWidth) / 2;
        int x = viewCenter;
        if (viewCenter < 0) {
            x = 0;
        }

        for (int i = 0; i < this.count; ++i) {
            x += this.radiusPx + this.strokePx;
            if (position == i) {
                return x;
            }

            x += this.radiusPx + this.paddingPx;
        }

        return x;
    }

    private Pair<Integer, Float> getProgress(int position, float positionOffset) {
        boolean isRightOverScrolled = position > this.selectedPosition;
        boolean isLeftOverScrolled = position + 1 < this.selectedPosition;
        if (isRightOverScrolled || isLeftOverScrolled) {
            this.selectedPosition = position;
        }

        boolean isSlideToRightSide = this.selectedPosition == position && positionOffset != 0.0F;
        int selectingPosition;
        float selectingProgress;
        if (isSlideToRightSide) {
            selectingPosition = position + 1;
            selectingProgress = positionOffset;
        } else {
            selectingPosition = position;
            selectingProgress = 1.0F - positionOffset;
        }

        if (selectingProgress > 1.0F) {
            selectingProgress = 1.0F;
        } else if (selectingProgress < 0.0F) {
            selectingProgress = 0.0F;
        }

        return new Pair(Integer.valueOf(selectingPosition), Float.valueOf(selectingProgress));
    }

    private int calculateActualViewWidth() {
        int width = 0;
        int diameter = this.radiusPx * 2 + this.strokePx;

        for (int i = 0; i < this.count; ++i) {
            width += diameter;
            if (i < this.count - 1) {
                width += this.paddingPx;
            }
        }

        return width;
    }
}
