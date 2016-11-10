package com.ds.master.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2016/10/10.
 */
public class RunAppInfo {

    public String[] pkgList;
    public String appName;
    public String appMemery;
    public Drawable appIcon;
    public boolean isSystemApp;
    public boolean isClear;

    /**
     *
     * @param appName :应用名称
     * @param appMemery :应用所占内存
     * @param appIcon ：应用图标
     * @param isSystemApp :是否是系统应用
     */
    public RunAppInfo(String[] pkgList,String appName, String appMemery, Drawable appIcon, boolean isSystemApp){
        this.pkgList = pkgList;
        this.appName = appName;
        this.appMemery = appMemery;
        this.appIcon = appIcon;
        this.isSystemApp = isSystemApp;
    }
}
