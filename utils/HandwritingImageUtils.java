package com.ds.getfrominternet.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/10/27.
 */

public class HandwritingImageUtils {
    private static HandwritingImageUtils imageUtils;
    private static Context context;

    private HashMap<String,SoftReference<Bitmap>> maps = new HashMap<>();



    private HandwritingImageUtils(){

    }

    public static HandwritingImageUtils getIntance(Context context){
        HandwritingImageUtils.context = context;
        synchronized (HandwritingImageUtils.class){
            if (imageUtils == null){
                imageUtils = new HandwritingImageUtils();
            }
        }
        return imageUtils;
    }


    public Bitmap getImage(String path){
        if(getByMemery(path) == null){
            if(getByLocal(path) == null){
                return getByInternet(path);
            }else{
                return getByLocal(path);
            }
        }else{
            return getByMemery(path);
        }
    }

    /**
     * 从内存中获取
     * @param path
     * @return
     */
    private Bitmap getByMemery(final String path){
        Bitmap bitmap = null;
        if(maps.containsKey(path)){
            bitmap = maps.get(path).get();
        }
        return bitmap;
    }

    /**
     * 读取本地
     * @param path
     * @return
     */
    private Bitmap getByLocal(final String path) {
        Bitmap bitmap = null;
        File file = new File(context.getCacheDir(),MD5Utils.encode(path));
        if(file.exists()) {
            bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            setMemery(path,bitmap);
        }
        return bitmap;
    }

    /**
     * 从网络获取
     * @param path
     * @return
     */
    private Bitmap getByInternet(final String path) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.connect();
            if (conn.getResponseCode() == 200) {
                InputStream inputStream = conn.getInputStream();
                //拿到图片
                bitmap = BitmapFactory.decodeStream(inputStream);

                //设置本地
                setLocal(path,inputStream);

                //设置内存
                setMemery(path,bitmap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 放入本地
     * @param path
     * @param inputStream
     */
    private void setLocal(String path,InputStream inputStream){
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(MD5Utils.encode(path)));
            byte[] bytes = new byte[1024];
            int len = 0;
            while((len = inputStream.read(bytes,0,1024)) != -1){
                bos.write(bytes,0,len);
            }
            bos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(bos!= null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 放入缓存
     * @param path
     * @param bitmap
     */
    private void setMemery(String path,Bitmap bitmap){
        maps.put(path,new SoftReference<Bitmap>(bitmap));
    }
}
