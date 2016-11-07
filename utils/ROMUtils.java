package com.ds.master.utils;

import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

/**
 * Created by Administrator on 2016/10/12.
 */
public class ROMUtils {

    /** 获取手机 */
    private static StatFs statFs;

    static {
        statFs = new StatFs(Environment.getRootDirectory().getPath());
    }

    /**
     * 获取手机内部可用空间 byte
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
     * 获取手机内部总空间 byte
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
