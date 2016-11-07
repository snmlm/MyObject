package com.ds.master.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.ds.master.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/10/12.
 */
public class CircleView extends View {

    /** 开始角度 */
    private int mStartAngle;
    /** 扫描角度 */
    private int mSweepAngle;
    /** 画笔 */
    private Paint mPaint;
    /** 高度 */
    private int mMeasuredHeight;
    /** 宽度 */
    private int mMeasuredWidth;
    /** 规格 */
    private RectF rectF;
    private TypedArray typedArray;
    private int color;

    public CircleView(Context context) {
        this(context,null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //属性数组
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleView);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//抗锯齿
        mStartAngle = typedArray.getInteger(R.styleable.CircleView_StartAngle,-90);
        color = typedArray.getColor(R.styleable.CircleView_Color, Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.YELLOW);
        canvas.drawArc(rectF, mStartAngle, 360,true, mPaint);
        mPaint.setColor(color);
        canvas.drawArc(rectF, mStartAngle, mSweepAngle,true, mPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mMeasuredHeight = getMeasuredHeight();
        mMeasuredWidth = getMeasuredWidth();
        rectF = new RectF(0, 0, mMeasuredWidth, mMeasuredHeight);
    }

    /**
     * 设置角度  进行动画  先减少 后增加
     * @param angle
     */
    public void setSweepAngle(final int angle){
        mSweepAngle = 0;
        new Timer(false).schedule(new TimerTask() {
            @Override
            public void run() {
                mSweepAngle+=10;//每次增加10度
                postInvalidate();//重绘
                if(mSweepAngle > angle){
                    mSweepAngle = angle;
                    postInvalidate();//重绘
                    cancel();
                }
            }
        },0,100);
    }
}
