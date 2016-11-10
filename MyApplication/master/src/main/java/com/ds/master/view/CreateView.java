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
 * Created by Administrator on 2016/9/28.
 */
public class CreateView extends View {

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
    /** 属性数组 */
    private TypedArray mTypedArray;
    /** 标志位 扫描角度增加还是减少  0代表减少 1代表增加 */
    private int tag = 0;
    /** 标志位 是否正在运行 */
    private boolean isRuning = false;
    /** 监听更新 */
    private onUpDateListener listener;

    public CreateView(Context context) {
        this(context,null);
    }

    public CreateView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CreateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取属性数组
        mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.CreateView);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//抗锯齿
        //设置颜色，默认绿色
        mPaint.setColor(mTypedArray.getColor(R.styleable.CreateView_arcColor, Color.GREEN));
        //设置开始角度，默认-90
        mStartAngle = mTypedArray.getInteger(R.styleable.CreateView_arcStartAngle,-90);
        //设置扫描角度，默认0
        mSweepAngle = mTypedArray.getInteger(R.styleable.CreateView_arcSweepAngle,0);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(new RectF(0,0,mMeasuredWidth,mMeasuredHeight), mStartAngle, mSweepAngle,true, mPaint);
        if(listener!=null)listener.setOnUpDate(mSweepAngle);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mMeasuredHeight = getMeasuredHeight();
        mMeasuredWidth = getMeasuredWidth();
    }

    public interface onUpDateListener{
        void setOnUpDate(int sweepAngle);
    }

    public void setOnUpDateListener(onUpDateListener listener){

        this.listener = listener;
    }

    /**
     * 设置角度  进行动画  先减少 后增加
     * @param angle
     */
    public void setSweepAngle(final int angle){
        if(isRuning)return;
        isRuning = true;
        new Timer(false).schedule(new TimerTask() {
            @Override
            public void run() {
                switch (tag){
                    case 0:
                        mSweepAngle-=10;//每次减少10度
                        postInvalidate();//重绘
                        if(mSweepAngle < 0){
                            mSweepAngle = 0;
                            postInvalidate();//重绘
                            tag = 1;
                        }
                        break;
                    case 1:
                        mSweepAngle+=10;//每次增加10度
                        postInvalidate();//重绘
                        if(mSweepAngle > angle){
                            mSweepAngle = angle;
                            postInvalidate();//重绘
                            cancel();
                            isRuning = false;
                            tag = 0;
                        }
                        break;
                }
            }
        },0,20);
    }
}
