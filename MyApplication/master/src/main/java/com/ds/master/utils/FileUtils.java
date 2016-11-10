package com.ds.master.utils;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Administrator on 2016/9/29.
 */
public class FileUtils {

    private static ObjectInputStream ois;
    private static ObjectOutputStream oos;
    private static boolean[] mSetting;

    /** 从本地读取用户信息 */
    public static boolean[] readInfo(Context context) {
        File file = new File(context.getFilesDir(),"info.txt");
        if(file.exists()){//文件存在
            try {
                ois = new ObjectInputStream(new FileInputStream(file));
                mSetting = (boolean[]) ois.readObject();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }finally {
                if(ois!=null){
                    try {
                        ois.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return mSetting;
        }else {
           return null;
        }

    }

    /** 保存密码 */
    public static void saveInnfo(Context context,boolean[] setting) {
        File file = new File(context.getFilesDir(),"info.txt");
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(setting);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(oos!=null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
