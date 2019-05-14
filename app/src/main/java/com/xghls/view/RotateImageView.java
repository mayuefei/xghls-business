package com.xghls.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;

import com.xghls.R;


/**
 * 自动旋转ImageView,加载中。。。显示的那个
 */
public class RotateImageView extends android.support.v7.widget.AppCompatImageView {
    public RotateImageView(Context context) {
        this(context, null);
    }

    public RotateImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RotateImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        isAnimStart = false;
    }

    private boolean isAnimStart;


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isAnimStart) {
            Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.anim_loading);
            animation.setInterpolator(new LinearInterpolator());
            startAnimation(animation);
            isAnimStart = true;
        }
    }


    @Override
    public void invalidate() {
        isAnimStart = false;
        super.invalidate();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
    }
}
