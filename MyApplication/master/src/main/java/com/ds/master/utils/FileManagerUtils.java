package com.ds.master.utils;

import android.content.Context;
import android.os.Environment;
import android.text.format.Formatter;
import android.util.Log;

import com.ds.master.bean.FileInfo;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/10/25.
 */

public class FileManagerUtils implements Serializable{


    private static final long serialVersionUID = -6696104586397549515L;

    private static final String TAG = "FileManagerUtils";
    private static FileManagerUtils utils ;
    private Context context;
    private static File mDtaFile;
    private static File mSdFile;

    public static int TYPE_ALL = 0;
    public static int TYPE_IN = 1;
    public static int TYPE_OUT = 2;


    /** 全部列表 */
    public List<FileInfo> mAllList;
    /** 全部列表 大小 */
    public long mAllSize;
    /** 文档列表 */
    public List<FileInfo> mTxtList;
    /** 文档列表 大小 */
    public long mTxtSize;
    /** 视频列表 */
    public List<FileInfo> mVideoList;
    /** 视频列表 大小 */
    public long mVideoSize;
    /** 音频列表 */
    public List<FileInfo> mAudioList;
    /** 音频列表 大小 */
    public long mAudioSize;
    /** 图片列表 */
    public List<FileInfo> mImageList;
    /** 图片列表 大小 */
    public long mImageSize;
    /** 压缩包列表 */
    public  List<FileInfo> mZipList;
    /** 压缩包列 大小 */
    public long mZipSize;
    /** 软件包列表 */
    public List<FileInfo> mApkList;
    /** 软件包列表 大小 */
    public long mApkSize;

    static {
        mDtaFile = Environment.getDataDirectory();
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            mSdFile = Environment.getExternalStorageDirectory();
        }
    }
    private onDataChangedListener mDataChangedListener;
    private onFinishistener mFinishistener;

    private FileManagerUtils(final Context context){
        this.context = context;
        mAllList = new ArrayList<>();
        mTxtList = new ArrayList<>();
        mVideoList = new ArrayList<>();
        mAudioList = new ArrayList<>();
        mImageList = new ArrayList<>();
        mZipList = new ArrayList<>();
        mApkList = new ArrayList<>();
    }

    public static FileManagerUtils  initFileManagerUtils(Context context){
        synchronized (FileManagerUtils.class){
            if(utils == null){
                utils = new FileManagerUtils(context);
            }
        }

        return utils;
    }


    public void setList(int type){
        mAllList.clear();
        mTxtList.clear();
        mVideoList.clear();
        mAudioList.clear();
        mImageList.clear();
        mZipList.clear();
        mApkList.clear();
        mAllSize = 0;
        mTxtSize = 0;
        mVideoSize = 0;
        mAudioSize = 0;
        mImageSize = 0;
        mZipSize = 0;
        mApkSize = 0;
        switch (type){
            case 0:
                //ergodicPath(mDtaFile);
                ergodicPath(mSdFile);
                if(mFinishistener != null)
                    mFinishistener.run();
                break;
            case 1:
                //ergodicPath(mDtaFile);
                break;
            case 2:
                ergodicPath(mSdFile);
                break;
        }
    }

    public void ergodicPath(File file){
        if(file == null || !file.exists())return;
        if(file.isFile()&&file.length()>0){
            String[] iconAndType = FileIconAndType.getIconAndType(file);
            if(iconAndType[0] != null){
                FileInfo fileInfo = new FileInfo(iconAndType[0],file.getName(),
                        new Date(file.lastModified()).toString(), Formatter.formatFileSize(context,file.length()),
                        false);
                Log.d(TAG, fileInfo.toString());
                mAllList.add(fileInfo);
                mAllSize += file.length();
                switch (iconAndType[1]){
                    case FileIconAndType.TYPE_TXT:
                        mTxtList.add(fileInfo);
                        mTxtSize += file.length();
                        break;
                    case FileIconAndType.TYPE_VIDEO:
                        mVideoList.add(fileInfo);
                        mVideoSize += file.length();
                        break;
                    case FileIconAndType.TYPE_AUDIO:
                        mAudioList.add(fileInfo);
                        mAudioSize += file.length();
                        break;
                    case FileIconAndType.TYPE_IMAGE:
                        mImageList.add(fileInfo);
                        mImageSize += file.length();
                        break;
                    case FileIconAndType.TYPE_ZIP:
                        mZipList.add(fileInfo);
                        mZipSize += file.length();
                        break;
                    case FileIconAndType.TYPE_APK:
                        mApkList.add(fileInfo);
                        mApkSize += file.length();
                        break;
                }
                if(mDataChangedListener!=null)
                    mDataChangedListener.run(mAllSize,mTxtSize,mVideoSize,mAudioSize,mImageSize,mZipSize,mApkSize);
            }
        }
        //文件夹
        File[] files = file.listFiles();
        if(files == null)return;
        for (File file1 : files) {
            ergodicPath(file1);
        }
    }

    public interface onDataChangedListener extends Serializable{
        void run(long...params);
    }

    public void setOnDataChangedListener(onDataChangedListener listener){

        this.mDataChangedListener = listener;
    }


    public interface onFinishistener extends Serializable{
        void run();
    }

    public void setOnFinishistener(onFinishistener listener){

        this.mFinishistener = listener;
    }

}
