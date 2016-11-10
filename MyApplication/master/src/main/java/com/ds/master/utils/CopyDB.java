package com.ds.master.utils;

import android.content.res.AssetManager;

import com.ds.master.activity.BaseActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/10/17.
 */
public class CopyDB extends BaseActivity {

    private InputStream open;
    private FileOutputStream fos;

    @Override
    protected void initView() {
        AssetManager assets = getAssets();//获取assets文件下的数据库
        File file = new File("data/data/"+this.getPackageName()+"/assets");//数据库的文件放在地址
        File file1 = new File(file.getPath(), "commonnum.db");//创建文件
        if (!file.exists()||!file.isDirectory()){
            /**
             * @!file.exists() 文件不存在
             * !file.isDirectory() 文件目录不存在
             * */
            file.mkdir();//创建子目录
        }
        if (!file1.exists()||file1.length()==0){//
            try {
                open = assets.open("commonnum.db");
                byte[] buffer = new byte[1024];
                fos = new FileOutputStream(new File(file.getPath(),"commonnum.db"));
                int read = open.read(buffer);
                while (read != -1) {
                    fos.write(buffer, 0, read);
                    String string = new String(buffer, 0, read);
                    System.out.println(string);
                    read = open.read(buffer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (open!=null){
                    try {
                        open.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }if (fos!=null){
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    System.out.print("文件已在");
                }
            }
        }
    }
}

