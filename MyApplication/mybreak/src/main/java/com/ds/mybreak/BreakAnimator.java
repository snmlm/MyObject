package com.ds.mybreak;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import java.util.ArrayList;

/**
 * 击碎动画
 * Created by xxxxx on 2016/10/19.
 */
public class BreakAnimator extends ValueAnimator {
    /**
     * 分割  圆的半径
     */
    private int SEGMENT;

    /** 击碎的view */
    private BreakView mBreakView;
    /** view */
    private View mView;
    /** 数据存放 */
    private Bitmap mBitmap;
    /** 点击的点 */
    private Point mTouchPoint;
    /**
     * 相对于view的相对位置 x
     */
    private int offsetX;
    /**
     * 相对于view的相对位置 y
     */
    private int offsetY;


    /** 设置 */
    private BreakConfig mBreakConfig;


    /** 线集合 */
    private ArrayList<Path> pathArray;
    /** 线 */
    private Path onDrawPath;
    /** 对线的测量 */
    private PathMeasure onDrawPM;
    /**
     * 破碎点的集合
     */
    private RiftsPath[] riftsPaths;
    /**
     * 破碎点圆形
     */
    private Path[] circleRifts;
    /**
     * 圆的宽度
     */
    private int[] circleWidth;

    /**
     * 判断是否设置了圆的半径
     */
    private boolean hasCircleRifts = true;


    public BreakAnimator(BreakView breakView, View view, Bitmap bitmap, Point point, BreakConfig breakConfig){

        this.mBreakView = breakView;
        this.mView = view;
        this.mBitmap = bitmap;
        this.mTouchPoint = point;
        this.mBreakConfig = breakConfig;

        //线的集合
        pathArray = new ArrayList<>();
        onDrawPath = new Path();
        onDrawPM = new PathMeasure();
        riftsPaths = new RiftsPath[mBreakConfig.complexity];
        circleRifts = new Path[mBreakConfig.complexity];
        circleWidth = new int[mBreakConfig.complexity];
        SEGMENT = mBreakConfig.circleRiftsRadius;
        if(SEGMENT == 0) {
            hasCircleRifts = false;
            SEGMENT = 66;
        }

        Rect r = new Rect();
        //获取相对于全局的view
        mView.getGlobalVisibleRect(r);

        offsetX = mTouchPoint.x - r.left;
        offsetY = mTouchPoint.y - r.top;

        // Make the touchPoint be the origin of coordinates
        //使点击点 移动到原点 left- top- right+ bottom+
        r.offset(-mTouchPoint.x, -mTouchPoint.y);



        Rect brokenViewR = new Rect();
        //获取击碎view的全局位置
        mBreakView.getGlobalVisibleRect(brokenViewR);
        //点击点向左上移动
        mTouchPoint.x -= brokenViewR.left;
        mTouchPoint.y -= brokenViewR.top;

        buildBreakLines(r);
        buildBreakAreas(r);
        buildPieces();
        buildPaintShader();
        warpStraightLines();

        //设置float类型的值
        setFloatValues(0f,1f);
        //设置差值器  在动画开始的地方速率改变比较慢，然后开始加速
        setInterpolator(new AccelerateInterpolator(2.0f));
        //设置动画时长，单位是毫秒
        setDuration(mBreakConfig.breakDuration);
    }


    /**
     * 弯曲 直线
     */
    private void warpStraightLines() {

    }

    /**
     * 构建画笔着色器
     */
    private void buildPaintShader() {

    }

    /**
     * 构建零件
     */
    private void buildPieces() {

    }

    /**
     * 构建击碎面积
     * @param r
     */
    private void buildBreakAreas(Rect r) {
        //最小分割
        final int SEGMENT_LESS = SEGMENT * 7 / 9;
        //开始长度
        final int START_LENGTH = (int)(SEGMENT * 1.1);
        // The Circle-Rifts is just some isosceles triangles,圆的裂痕只是一些等腰三角形
        // "linkLen" is the length of oblique side  斜侧的长度
        float linkLen = 0;
        int repeat = 0;

        //当前轨道的测量
        PathMeasure pmNow = new PathMeasure();
        //之前轨道的测量
        PathMeasure pmPre = new PathMeasure();

        for(int i = 0; i < mBreakConfig.complexity; i++) {
            riftsPaths[i].startLength = Utils.dp2px(START_LENGTH);

            if (repeat > 0) {
                repeat--;
            } else {
                linkLen = Utils.nextInt(Utils.dp2px(SEGMENT_LESS),Utils.dp2px(SEGMENT));
                repeat = Utils.nextInt(3);
            }

            //头尾相连
            int iPre = (i - 1) < 0 ? mBreakConfig.complexity - 1 : i - 1;
            //设置道路
            pmNow.setPath(riftsPaths[i],false);
            pmPre.setPath(riftsPaths[iPre], false);

            if (hasCircleRifts && pmNow.getLength() > linkLen && pmPre.getLength() > linkLen) {

                float[] pointNow = new float[2];
                float[] pointPre = new float[2];

                circleWidth[i] = Utils.nextInt(Utils.dp2px(1)) + 1;
                circleRifts[i] = new Path();
                //在道路上 距离linkLen长度的点的位置
                pmNow.getPosTan(linkLen, pointNow, null);
                //移动到现在的点
                circleRifts[i].moveTo(pointNow[0], pointNow[1]);
                //在道路上 距离linkLen长度的点的位置
                pmPre.getPosTan(linkLen, pointPre, null);
                //连接之前的点
                circleRifts[i].lineTo(pointPre[0], pointPre[1]);

                // The area outside Circle-Rifts
                Path pathArea = new Path();
                pmPre.getSegment(linkLen, pmPre.getLength(), pathArea, true);
                pathArea.rLineTo(0, 0); // KITKAT(API 19) and earlier need it

                drawBorder(pathArea,riftsPaths[iPre].endPoint,
                        riftsPaths[i].points.get(riftsPaths[i].points.size() - 1),r);
                for (int j =  riftsPaths[i].points.size() - 2; j >= 0; j--)
                    pathArea.lineTo(riftsPaths[i].points.get(j).x, riftsPaths[i].points.get(j).y);
                pathArea.lineTo(pointNow[0], pointNow[1]);
                pathArea.lineTo(pointPre[0], pointPre[1]);
                pathArea.close();
                pathArray.add(pathArea);

            }
        }
    }

    /**
     * 画线
     * @param path
     * @param pointStart
     * @param pointEnd
     * @param r
     */
    public void drawBorder(Path path,Point pointStart,Point pointEnd,Rect r){

    }

    /**
     * 构建击碎线
     * @param r 位置
     */
    private void buildBreakLines(Rect r) {
        RiftsPath[] baseLines = new RiftsPath[mBreakConfig.complexity];
        buildBaselines(baseLines, r);
    }

    /**
     * 构建基础线
     * @param baseLines
     * @param r
     */
    private void buildBaselines(RiftsPath[] baseLines,Rect r){
        for(int i = 0; i < mBreakConfig.complexity; i++){
            baseLines[i] = new RiftsPath();
            //设置绘制起点
            baseLines[i].moveTo(0,0);
        }
        //设置第一条线
        buildFirstLine(baseLines[0], r);

        //第一条线的角度
        int angle = (int)(Math.toDegrees(Math.atan(
                (float)(-baseLines[0].endPoint.y) / baseLines[0].endPoint.x)));

        // 点击点到四个角的角度
        int[] angleBase = new int[4];
        angleBase[0] = (int)(Math.toDegrees(Math.atan((float)(-r.top) / (r.right))));
        angleBase[1] = (int)(Math.toDegrees(Math.atan((float)(-r.top) / (-r.left))));
        angleBase[2] = (int)(Math.toDegrees(Math.atan((float)(r.bottom) / (-r.left))));
        angleBase[3] = (int)(Math.toDegrees(Math.atan((float)(r.bottom) / (r.right))));


        //判断在哪个象限
        if(baseLines[0].endPoint.x < 0) // 2-quadrant,3-quadrant
            angle += 180;
        else if(baseLines[0].endPoint.x > 0 && baseLines[0].endPoint.y > 0) // 4-quadrant
            angle += 360;

        // Random angle range
        int range = 360 / mBreakConfig.complexity / 3;
        int angleRandom;

        for(int i = 1; i<mBreakConfig.complexity; i++) {
            angle = angle + 360 / mBreakConfig.complexity;
            if (angle >= 360)
                angle -= 360;

            angleRandom = angle + Utils.nextInt(-range, range);
            if (angleRandom >= 360)
                angleRandom -= 360;
            else if (angleRandom < 0)
                angleRandom += 360;

            baseLines[i].obtainEndPoint(angleRandom,angleBase,r);
            baseLines[i].lineToEnd();
        }
    }

    private void buildFirstLine(RiftsPath path, Rect r){
        //范围 扩大了
        int[] range=new int[]{-r.left,-r.top,r.right,r.bottom};
        //获取范围中最大值
        int max = -1;
        int maxId = 0;
        for(int i = 0; i < 4; i++) {
            if(range[i] > max) {
                max = range[i];
                maxId = i;
            }
        }
        //判断点击位置
        switch (maxId){
            case 0://左边上的某点
                path.setEndPoint(r.left, Utils.nextInt(r.height()) + r.top);
                break;
            case 1://上边上的某点
                path.setEndPoint(Utils.nextInt(r.width()) + r.left, r.top);
                break;
            case 2://右边上的某点
                path.setEndPoint(r.right, Utils.nextInt(r.height()) + r.top);
                break;
            case 3://下边上的某点
                path.setEndPoint(Utils.nextInt(r.width()) + r.left, r.bottom);
                break;
        }
        //连接到这个点
        path.lineToEnd();
    }
}
