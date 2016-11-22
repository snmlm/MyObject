package com.ds.myapp.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;

import java.lang.reflect.Field;

/**
 * 自定义ViewPager:可以控制页面切换的速度
 */

public class ViewPagerForScrollerSpeed extends ViewPager {

    Context context;

    private int speed = 0;// 毫秒

    public ViewPagerForScrollerSpeed(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public ViewPagerForScrollerSpeed(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public void init() {
        controlViewPagerSpeed();
    }

    // 设置滚动速度
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    FixedSpeedScroller mScroller = null;

    private void controlViewPagerSpeed() {
        try {
            Field mField;
            mField = ViewPager.class.getDeclaredField("mScroller");
            mField.setAccessible(true);
            mScroller = new FixedSpeedScroller(context, new AccelerateInterpolator());
            mScroller.setmDuration(speed);
            mField.set(this, mScroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}