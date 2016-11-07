package com.ds.master.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import com.ds.master.bean.RunAppInfo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * 运行内存工具类
 * Created by xxxxx on 2016/9/29.
 */
public class RAMUtils {

    /**
     * 获取可用内存
     * @param context 上下文
     * @return 返回可用内存 单位是b
     */
    public static long getUsableRAM(Context context){
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        return mi.availMem;
    }

    /**
     * 获取总内存
     * @return 返回b
     */
    public static long getTotalRAM(){
        // /proc/meminfo读出的内核信息进行解释
        String path = "/proc/meminfo";
        String content = "";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(path));
            content =  br.readLine();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // 截取字符串信息
        content = content.substring(content.indexOf(':') + 1, content.indexOf('k')).trim();
        return Long.parseLong(content)*1024;//b
    }

    /**
     * 返回可用百分比
     * @param context ： 上下文
     * @return 返回float
     */
    public static float getUsableRAMPercent(Context context){
        return getUsableRAM(context) * 1.0f / getTotalRAM();
    }


    /**
     * 清理内存
     * @param context ： 上下文
     */
    public static void clear(Context context){
        ActivityManager activityManger=(ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //获取当前进程列表
        List<ActivityManager.RunningAppProcessInfo> list = activityManger.getRunningAppProcesses();
        if(list!=null)
            for (ActivityManager.RunningAppProcessInfo apinfo : list){
                //获取进程内的线程
                String[] pkgList = apinfo.pkgList;
                //判断等级
                if(apinfo.importance > ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND)
                {
                    //Process.killProcess(apinfo.pid);
                    for(int j=0;j<pkgList.length;j++)
                    {
                        //2.2以上是过时的,请用killBackgroundProcesses代替
                        /**清理不可用的内容空间**/
                        //activityManger.restartPackage(pkgList[j]);
                        activityManger.killBackgroundProcesses(pkgList[j]);
                    }
                }
            }
    }


    public static void clearById(Context context, List<RunAppInfo> list){
        ActivityManager activityManger=(ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //获取当前进程列表
        //List<ActivityManager.RunningAppProcessInfo> list = activityManger.getRunningAppProcesses();
        if(list!=null){
            for (RunAppInfo runAppInfo : list) {
                for (String s : runAppInfo.pkgList) {
                    Log.e("xxxxx", s + "  "+runAppInfo.isClear);
                }
                if(runAppInfo.isClear){
                    for (String s : runAppInfo.pkgList) {
                        activityManger.killBackgroundProcesses(s);
                    }
                }

            }
        }


    }
}
