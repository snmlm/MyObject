package com.ds.mybreak;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

/**
 * Created by Administrator on 2016/10/19.
 */
public class BreakView extends View {
    public BreakView(Context context) {
        super(context);
        //设置为硬件加速
        setLayerType(View.LAYER_TYPE_HARDWARE, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
