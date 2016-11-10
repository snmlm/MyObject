package com.ds.mybreak;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/19.
 */
public class RiftsPath extends Path{

    public Point endPoint;
    public float startLength;
    public boolean straight;
    public ArrayList<Point> points;

    public RiftsPath(){
        super();
        points = new ArrayList<>();
        startLength = -1;
        endPoint = new Point();
    }

    public RiftsPath(RiftsPath p){
        super(p);
        points = (ArrayList)p.points.clone();
        startLength = p.startLength;
        endPoint = new Point(p.endPoint);
    }

    /**
     * 连接到endPoint
     */
    public void lineToEnd(){
        lineTo(endPoint.x,endPoint.y);
    }

    public void setEndPoint(int endX,int endY) {
        endPoint.set(endX,endY);
    }

    /**
     *
     * @param angleRandom 随机角度
     * @param angleBase 角度基础数组
     * @param r 范围 位置
     */
    public void obtainEndPoint(int angleRandom,int[] angleBase,Rect r){

        //正切值  角度化弧度
        float gradient = -(float) Math.tan(Math.toRadians(angleRandom));
        int endX = 0, endY = 0;
        //0~90
        if (angleRandom >= 0 && angleRandom < 90) {
            if (angleRandom < angleBase[0]) {
                endX = r.right;
                endY = (int)(endX * gradient);
            } else if (angleRandom > angleBase[0]) {
                endY = r.top;
                endX = (int) (endY / gradient);
            } else if (angleRandom == angleBase[0]) {
                endY = r.top;
                endX = r.right;
            }
        } else if (angleRandom > 90 && angleRandom <= 180) {
            if (180 - angleRandom < angleBase[1]) {
                endX = r.left;
                endY = (int) (endX * gradient);
            } else if (180 - angleRandom > angleBase[1]) {
                endY = r.top;
                endX = (int) (endY / gradient);
            } else if (180 - angleRandom == angleBase[1]) {
                endY = r.top;
                endX = r.left;
            }
        } else if (angleRandom > 180 && angleRandom < 270) {
            if (angleRandom - 180 < angleBase[2]) {
                endX = r.left;
                endY = (int) (endX * gradient);
            } else if (angleRandom - 180 > angleBase[2]) {
                endY = r.bottom;
                endX = (int) (endY / gradient);
            } else if (angleRandom - 180 == angleBase[2]) {
                endY = r.bottom;
                endX = r.left;
            }
        } else if (angleRandom > 270 && angleRandom < 360) {
            if (360 - angleRandom < angleBase[3]) {
                endX = r.right;
                endY = (int) (endX * gradient);
            } else if (360 - angleRandom > angleBase[3]) {
                endY = r.bottom;
                endX = (int) (endY / gradient);
            } else if (360 - angleRandom == angleBase[3]) {
                endY = r.bottom;
                endX = r.right;
            }
        }
        else if(angleRandom == 90) {
            endX = 0;
            endY = r.top;
        }
        else if(angleRandom == 270) {
            endX = 0;
            endY = r.bottom;
        }
        endPoint.set(endX,endY);
    }
}
