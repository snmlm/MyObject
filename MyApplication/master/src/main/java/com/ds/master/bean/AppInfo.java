package com.ds.master.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2016/10/12.
 */
public class AppInfo {

    public static final int SYSTEM_TAG = 0;
    public static final int NOT_SYSTEM_TAG = 1;
    public static final int ALL_TAG = 2;

    /** 是否删除 */
    public boolean isClear;
    /** 应用名称 */
    public String appName;
    /** 应用包名 */
    public String appPackage;
    /** 版本号 */
    public String appVersionName;
    /** 版本玛 */
    public int appVersionCode;
    /** 图标 */
    public Drawable appIcon;

    public AppInfo(boolean isClear, String appName, String appPackage,
                   String appVersionName, int appVersionCode, Drawable appIcon){

        this.isClear = isClear;
        this.appName = appName;
        this.appPackage = appPackage;
        this.appVersionName = appVersionName;
        this.appVersionCode = appVersionCode;
        this.appIcon = appIcon;
    }
}
