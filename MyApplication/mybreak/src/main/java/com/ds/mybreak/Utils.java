package com.ds.mybreak;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

import java.util.Random;


/**
 * 工具类
 * Created by xxxxx on 2016/10/19.
 */
public class Utils {

    private Utils() {}

    /**
     * 屏幕宽
     */
    static int screenWidth;
    /**
     * 屏幕高
     */
    static int screenHeight;
    /**
     * 随机数
     */
    private static Random random = new Random();
    /**
     * 密度  获取屏幕密度
     */
    private static final float DENSITY = Resources.getSystem().getDisplayMetrics().density;
    /**
     * 画布
     */
    private static final Canvas mCanvas = new Canvas();

    /**
     * dp转px
     * @param dp
     * @return
     */
    static int dp2px(int dp) {
        return Math.round(dp * DENSITY);
    }

    /**
     * view转换成bitmap
     * @param view
     * @return
     */
    static Bitmap convertViewToBitmap(View view) {
        view.clearFocus();
        Bitmap bitmap = createBitmapSafely(view.getWidth(),
                view.getHeight(), Bitmap.Config.ARGB_4444, 2);
        if (bitmap != null) {
            mCanvas.setBitmap(bitmap);
            mCanvas.translate(-view.getScrollX(), -view.getScrollY());
            view.draw(mCanvas);
            mCanvas.setBitmap(null);
        }
        return bitmap;
    }

    /**
     * 安全的创建bitmap
     * @param width
     * @param height
     * @param config
     * @param retryCount
     * @return
     */
    static Bitmap createBitmapSafely(int width, int height, Bitmap.Config config, int retryCount) {
        while(retryCount-- > 0) {
            try {
                return Bitmap.createBitmap(width, height, config);
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
                System.gc();
            }
        }
        return null;
    }

    /**
     * 下一个Int 假设 a<b a ~ b之间的数（不包含b）
     * @param a
     * @param b
     * @return
     */
    static int nextInt(int a, int b){
        return Math.min(a,b) + random.nextInt(Math.abs(a - b));
    }

    /**
     * 随机 假设a>0 0~a的数 不含a
     * @param a
     * @return
     */
    static int nextInt(int a){
        return random.nextInt(a);
    }

    /**
     * 下一个Int 假设 a<b a ~ b之间的数（不包含b）
     * @param a
     * @param b
     * @return
     */
    static float nextFloat(float a, float b){
        return Math.min(a,b) + random.nextFloat() * Math.abs(a - b);
    }

    /**
     * 随机 假设a>0 0~a的数 不含a
     * @param a
     * @return
     */
    static float nextFloat(float a){
        return random.nextFloat() * a;
    }

    /**
     * 随机boolean
     * @return
     */
    static boolean nextBoolean(){
        return random.nextBoolean();
    }

}
