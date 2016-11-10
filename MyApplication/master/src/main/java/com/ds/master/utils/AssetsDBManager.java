package com.ds.master.utils;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 帮助读取文件夹中的数据库
 */
public class AssetsDBManager {
    private static final String TAG = "AssetsDBManager";

    //拷贝数据库到指定目录
    public static void copyAssetsFileToFile(Context context, String path, File toFile) throws IOException {
        //打印日志
        Log.d(TAG,"copyAssetsFieToFile start");
        Log.d(TAG,"file path:" + path);
        //拿到文件绝对路径，可以看文件最后去哪
        Log.d(TAG,"toFile path:" + toFile.getAbsolutePath());


        //输入流
        InputStream is = null;

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            is = context.getAssets().open(path);

            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(new FileOutputStream(toFile, false));//里边放路径，布尔值

            //IO
            int len = 0;
            byte[] buffer = new byte[1024];

            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            bos.flush();//刷新数据流
        } catch (IOException e) {
            e.printStackTrace();
        }finally {//关闭流
            bos.close();
            bis.close();
            is.close();
        }

    }
}
