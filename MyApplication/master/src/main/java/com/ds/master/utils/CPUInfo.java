package com.ds.master.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/10/13.
 */
public class CPUInfo {


    // 获取CPU名字
    //# cat cpuinfo
    //cat cpuinfo
    public static String getCpuName() {
        try {
            FileReader fr = new FileReader("/proc/cpuinfo");
            BufferedReader br = new BufferedReader(fr);
            String text = br.readLine();//获取第一行
            Log.e("xxxxx",text);
            String[] array = text.split(":\\s+", 2);//:\\s+ 匹配多个空格
            if (fr != null) fr.close();
            return array[1];
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    static class CpuFilter implements FileFilter {
        @Override
        public boolean accept(File pathname) {
            //Check if filename is "cpu", followed by a single digit number
            if(Pattern.matches("cpu[0-9]", pathname.getName())) {
                return true;
            }
            return false;
        }
    }

    public static String getCpuNumber(){
        File dir = new File("/sys/devices/system/cpu/");
        //Filter to only list the devices we care about
        File[] files = dir.listFiles(new CpuFilter());
        Log.e("xxxxx",files.length + "");
        //Return the number of cores (virtual CPU devices)
        return files.length+"";
        //return null;
    }



}
