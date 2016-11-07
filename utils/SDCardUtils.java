package com.ds.master.utils;

import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

/**
 * SD卡工具类
 * Created by xxxxx on 2016/9/30.
 */
public class SDCardUtils {

    /** 获取SD卡 */
    private static StatFs statFs;
    private static long availableBlocksLong;

    static {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        }else{
            statFs = null;
        }
    }

    /**
     * 获取SD卡可用空间 byte
     * @return
     */
    public static Long getAvailableCount(){
        if(statFs != null){
            if(Build.VERSION.SDK_INT >= 18){
                return statFs.getAvailableBlocksLong()*statFs.getBlockSizeLong();
            }else{
                return (long)statFs.getAvailableBlocks()*(long)statFs.getBlockSize();
            }
        }
        return null;
    }

    /**
     * 获取SD卡总空间 byte
     * @return
     */
    public static Long getCount(){
        if(statFs != null){
            if(Build.VERSION.SDK_INT >= 18){
                return statFs.getBlockCountLong()*statFs.getBlockSizeLong();
            }else{
                return (long)statFs.getBlockCount()*(long)statFs.getBlockSize();
            }
        }
        return null;
    }

    /**
     * 获取可用所占比例
     * @return float：浮点类型
     */
    public static float getPerent(){
        return getAvailableCount() * 1.0f /getCount();
    }
}
