package com.ds.myapp.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/11/8.
 */

public class Image3DView extends ImageView {

    private final Camera mCamera;
    private final Matrix mMatrix;
    private Bitmap mBitmap;
    /**
     * 当前图片对应的下标
     */
    private int mIndex;
    /**
     * 在前图片在X轴方向滚动的距离 
     */
    private int mScrollX;
    /**
     * Image3DSwitchView控件的宽度     
     */
    private int mLayoutWidth;
    /**
     * 图片的宽度
     */
    private int mWidth;

    public Image3DView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCamera = new Camera();
        mMatrix = new Matrix();
    }

    /**
     * 初始化view所有的信息，包括图片的高度，背景
     */
    public void initImageViewBitmap() {
        if (mBitmap == null) {
            setDrawingCacheEnabled(true);//可以缓存
            buildDrawingCache();//创建缓存
            mBitmap = getDrawingCache();//获取图片缓存
        }
        //mLayoutWidth = Image3DSwitchView.mWidth;
        //mWidth = getWidth() + Image3DSwitchView.IMAGE_PADDING * 2;
    }

    /**
     * 设置旋转角度
     *
     * @param index   当前
     * @param scrollX
     */
    public void setRotateData(int index, int scrollX) {
        mIndex = index;
        mScrollX = scrollX;
    }


    /**
     * 回收当前的Bitmap对象，以释放内存。
     */
    public void recycleBitmap() {
        if (mBitmap != null && !mBitmap.isRecycled()){
            mBitmap.recycle();
        }
    }

    /**
     * 加载图片资源
     * @param resId
     */
    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        mBitmap = null;
        initImageViewBitmap();
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        mBitmap = null;
        initImageViewBitmap();
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        mBitmap = null;
        initImageViewBitmap();
    }

    @Override
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        mBitmap = null;
        initImageViewBitmap();
    }
}
