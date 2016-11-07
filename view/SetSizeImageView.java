package com.ds.myapp.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/11/3.
 */

public class SetSizeImageView extends ImageView {
    public SetSizeImageView(Context context) {
        this(context,null);
    }

    public SetSizeImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SetSizeImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void setImageBitmap(Bitmap bm) {
        setBackground(new BitmapDrawable());
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void setImageDrawable(Drawable drawable) {
        setBackground(drawable);
    }
}
