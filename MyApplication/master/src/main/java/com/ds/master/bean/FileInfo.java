package com.ds.master.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/25.
 */

public class FileInfo implements Serializable{


    private static final long serialVersionUID = 5777151906803030397L;
    public String fileIcon;
    public String fileName;
    public String fileTime;
    public String fileSize;
    public boolean isClear;

    public FileInfo(String fileIcon, String fileName,
                    String FileTime, String FileSize, boolean isClear){
        this.fileIcon = fileIcon;
        this.fileName = fileName;
        this.fileTime = FileTime;
        this.fileSize = FileSize;
        this.isClear = isClear;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "fileIcon='" + fileIcon + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileTime='" + fileTime + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", isClear=" + isClear +
                '}';
    }
}
