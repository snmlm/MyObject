package com.ds.slidelayout;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2016/9/19.
 */
public class SlideLayout extends RelativeLayout {

    /**
     * 底布局
     */
    private LinearLayout mBottomLayout;
    /**
     * 左侧布局
     */
    private LinearLayout mLeftLayout;
    /**
     * 主布局
     */
    private LinearLayout mMainLayout;
    /**
     * 拖动工具
     */
    private ViewDragHelper mViewDragHelper;
    /**
     * 控件宽度
     */
    private int mWidth;
    /**
     * 控件高度
     */
    private int mHeight;
    /**
     *  横向限制
     */
    private int limit;


    public SlideLayout(Context context) {
        this(context,null);
    }

    public SlideLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SlideLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //拖动工具
        mViewDragHelper = ViewDragHelper.create(SlideLayout.this, mCallBack);

    }

    /**
     * 工具类回调函数
     */
    private ViewDragHelper.Callback mCallBack = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            if(child == mBottomLayout)return false;
            return true;
        }

        /**
         * 横向移动
         * @param child
         * @param left
         * @param dx
         * @return
         */
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if(child == mLeftLayout){
                return leftLimitLeftLayout(left);//限制下方布局不被限制 否则onViewPositionChanged中的dx不会为负数
            }
            return leftLimitMainLayout(left);
        }

        /**
         * 纵向移动
         * @param child
         * @param top
         * @param dy
         * @return
         */
        /*@Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return top;
        }*/

        /**
         * view位置改变
         * @param changedView
         * @param left
         * @param top
         * @param dx
         * @param dy
         */
        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            if(changedView == mLeftLayout){
                mMainLayout.layout(leftLimitMainLayout(dx*2+mMainLayout.getLeft()),0,mWidth+mMainLayout.getLeft(),mHeight);
                mLeftLayout.layout(dx+mLeftLayout.getLeft(),0,dx+mLeftLayout.getLeft()+mWidth,mHeight);
            }
            if(changedView == mMainLayout){
                //mMainLayout.layout(leftLimit(dx+mMainLayout.getLeft()),0,dx+mMainLayout.getRight(),mHeight);
                mLeftLayout.layout((int)(dx*0.5f+mLeftLayout.getLeft()+0.5f),0,mLeftLayout.getLeft()+mWidth,mHeight);
            }
            invalidate();//兼容低版本 强制重绘
        }

        /**
         * View释放被调用
         * @param releasedChild
         * @param xvel
         * @param yvel
         */
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            if(xvel == 0 && mMainLayout.getLeft()>limit/2){
                open();
            }else if(xvel > 0){
                open();
            }else {
                close();
            }
        }
    };

    /**
     * 关闭
     */
    private void close() {
        //返回ture表示动画还没完成
        if(mViewDragHelper.smoothSlideViewTo(mMainLayout,0,0)){
            ViewCompat.postInvalidateOnAnimation(SlideLayout.this);
        }
    }

    /**
     * 打开
     */
    private void open() {
        if(mViewDragHelper.smoothSlideViewTo(mMainLayout,limit,0)){
            ViewCompat.postInvalidateOnAnimation(SlideLayout.this);
        }
    }

    /**
     * 横向移动限制
     * @param left
     * @return
     */
    private int leftLimitLeftLayout(int left){
        int leftLimit = (int)(limit*0.5f+0.5f);
        if(left <=-leftLimit){
            left = -leftLimit;
        }else if(left >=leftLimit) {
            left = leftLimit;
        }
        return left;
    }

    /**
     * 横向移动限制
     * @param left
     * @return
     */
    private int leftLimitMainLayout(int left){
        if(left <=0){
            left = 0;
        }else if(left >=limit) {
            left = limit;
        }
        return left;
    }

    /**
     *  xml解析完毕
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mBottomLayout = (LinearLayout)getChildAt(0);
        mLeftLayout = (LinearLayout)getChildAt(1);
        mMainLayout = (LinearLayout)getChildAt(2);
    }

    /**
     * 拦截触摸事件
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //让工具去处理触摸事件
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    /**
     * 拦截触摸事件
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //工具处理点击事件
        mViewDragHelper.processTouchEvent(event);
        //持续接收
        return true;
    }

    /**
     * 拦截触摸事件
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = getMeasuredHeight();
        mWidth = getMeasuredWidth();
        limit = (int)(mWidth * 0.8f + 0.5f);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        //是否继续完成滑动，返回true，还需要继续滑动
        if(mViewDragHelper.continueSettling(true)){
            ViewCompat.postInvalidateOnAnimation(SlideLayout.this);
        }
    }
}
