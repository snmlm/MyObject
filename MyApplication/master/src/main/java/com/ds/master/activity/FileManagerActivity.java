package com.ds.master.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.Formatter;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ds.master.R;
import com.ds.master.bean.FileInfo;
import com.ds.master.utils.FileIconAndType;
import com.ds.master.utils.FileManagerUtils;
import com.ds.master.view.TitleBarView;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件管理
 * Created by xxxxx on 2016/9/28.
 */
public class FileManagerActivity extends BaseActivity {
    private static final String TAG = "FileManagerActivity";
    /**
     * 标记哪里来的
     */
    private String from;

    private TitleBarView mTbnFileTitle;
    private TextView mTvFileNumber;
    private RelativeLayout mRlayoutFileAll;
    private TextView mTvFileAll;
    private RelativeLayout mRlayoutFileDocument;
    private TextView mTvFileDocument;
    private RelativeLayout mRlayoutFileVideo;
    private TextView mTvFileVideo;
    private RelativeLayout mRlayoutFileVoice;
    private TextView mTvFileVoice;
    private RelativeLayout mRlayoutFileImage;
    private TextView mTvFileImage;
    private RelativeLayout mRlayoutFilePackage;
    private TextView mTvFilePackage;
    private RelativeLayout mRlayoutFileSoftware;
    private TextView mTvFileSoftware;
    private FileManagerUtils fileManagerUtils;

    private Handler handler;

    private List<TextView> mLists = new ArrayList<>();
    private RelativeLayout mrLayoutFileProgress;

    private void assignViews() {
        mTbnFileTitle = (TitleBarView) findViewById(R.id.tbn_file_title);
        mTvFileNumber = (TextView) findViewById(R.id.tv_file_number);
        mRlayoutFileAll = (RelativeLayout) findViewById(R.id.rlayout_file_all);
        mTvFileAll = (TextView) findViewById(R.id.tv_file_all);
        mRlayoutFileDocument = (RelativeLayout) findViewById(R.id.rlayout_file_document);
        mTvFileDocument = (TextView) findViewById(R.id.tv_file_document);
        mRlayoutFileVideo = (RelativeLayout) findViewById(R.id.rlayout_file_video);
        mTvFileVideo = (TextView) findViewById(R.id.tv_file_video);
        mRlayoutFileVoice = (RelativeLayout) findViewById(R.id.rlayout_file_voice);
        mTvFileVoice = (TextView) findViewById(R.id.tv_file_voice);
        mRlayoutFileImage = (RelativeLayout) findViewById(R.id.rlayout_file_image);
        mTvFileImage = (TextView) findViewById(R.id.tv_file_image);
        mRlayoutFilePackage = (RelativeLayout) findViewById(R.id.rlayout_file_package);
        mTvFilePackage = (TextView) findViewById(R.id.tv_file_package);
        mRlayoutFileSoftware = (RelativeLayout) findViewById(R.id.rlayout_file_software);
        mTvFileSoftware = (TextView) findViewById(R.id.tv_file_software);
        mrLayoutFileProgress = (RelativeLayout) findViewById(R.id.rlayout_file_progress);


        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    mrLayoutFileProgress.setVisibility(View.GONE);
                } else {
                    long[] prams = (long[]) msg.obj;
                    mTvFileNumber.setText(Formatter.formatFileSize(FileManagerActivity.this, prams[0]));
                    mTvFileAll.setText(Formatter.formatFileSize(FileManagerActivity.this, prams[0]));
                    mTvFileDocument.setText(Formatter.formatFileSize(FileManagerActivity.this, prams[1]));
                    mTvFileVideo.setText(Formatter.formatFileSize(FileManagerActivity.this, prams[2]));
                    mTvFileVoice.setText(Formatter.formatFileSize(FileManagerActivity.this, prams[3]));
                    mTvFileImage.setText(Formatter.formatFileSize(FileManagerActivity.this, prams[4]));
                    mTvFilePackage.setText(Formatter.formatFileSize(FileManagerActivity.this, prams[5]));
                    mTvFileSoftware.setText(Formatter.formatFileSize(FileManagerActivity.this, prams[6]));
                }

            }
        };
    }


    @Override
    protected void initView() {
        setContentView(R.layout.activity_file_manager);
        assignViews();

        Intent intent = getIntent();
        from = intent.getStringExtra("from");
        mTbnFileTitle.setTopTitleText(String.format(getResources().getString(R.string.top_title)
                , intent.getStringExtra("title")));

        new Thread() {
            @Override
            public void run() {
                fileManagerUtils = FileManagerUtils.initFileManagerUtils(FileManagerActivity.this);
                fileManagerUtils.setOnFinishistener(new FileManagerUtils.onFinishistener() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(1);
                    }
                });
                fileManagerUtils.setOnDataChangedListener(new FileManagerUtils.onDataChangedListener() {
                    @Override
                    public void run(long... params) {
                        Message msg = new Message();
                        msg.obj = params;
                        handler.sendMessage(msg);
                    }
                });

                fileManagerUtils.setList(FileManagerUtils.TYPE_ALL);
            }
        }.start();


    }

    @Override
    protected void setListener() {
        mTbnFileTitle.setOnClickListener(listener);
        mRlayoutFileAll.setOnClickListener(listener);
        mRlayoutFileDocument.setOnClickListener(listener);
        mRlayoutFileVideo.setOnClickListener(listener);
        mRlayoutFileVoice.setOnClickListener(listener);
        mRlayoutFileImage.setOnClickListener(listener);
        mRlayoutFilePackage.setOnClickListener(listener);
        mRlayoutFileSoftware.setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(FileManagerActivity.this, FileActivity.class);
            Bundle bundle = new Bundle();
            intent.putExtra("from", "manager");
            switch (v.getId()) {
                case R.id.ib_top_title_left://返回
                    intent = null;
                    Toast.makeText(FileManagerActivity.this, "文件管理", Toast.LENGTH_SHORT).show();
                    finish();
                    if (from.contains("home")) {
                        overridePendingTransition(R.anim.translate_t2b_enter,
                                R.anim.translate_t2b_exit);
                    }
                    break;
                case R.id.rlayout_file_all://全部
                    intent.putExtra("title", "全部");
                    intent.putExtra("type", FileIconAndType.TYPE_ALL);
                    bundle.putSerializable("data",new ArrayList<FileInfo>(fileManagerUtils.mAllList));
                    intent.putExtra("size",Formatter.formatFileSize(FileManagerActivity.this,fileManagerUtils.mAllSize));
                    break;
                case R.id.rlayout_file_document://文档
                    intent.putExtra("title", "文档");
                    intent.putExtra("type", FileIconAndType.TYPE_TXT);
                    bundle.putSerializable("data",new ArrayList<FileInfo>(fileManagerUtils.mTxtList));
                    intent.putExtra("size",Formatter.formatFileSize(FileManagerActivity.this,fileManagerUtils.mTxtSize));
                    break;
                case R.id.rlayout_file_video://视频
                    intent.putExtra("title", "视频");
                    intent.putExtra("type", FileIconAndType.TYPE_VIDEO);
                    bundle.putSerializable("data",new ArrayList<FileInfo>(fileManagerUtils.mVideoList));
                    intent.putExtra("size",Formatter.formatFileSize(FileManagerActivity.this,fileManagerUtils.mVideoSize));
                    break;
                case R.id.rlayout_file_voice://音频
                    intent.putExtra("title", "音频");
                    intent.putExtra("type", FileIconAndType.TYPE_AUDIO);
                    bundle.putSerializable("data",new ArrayList<FileInfo>(fileManagerUtils.mAudioList));
                    intent.putExtra("size",Formatter.formatFileSize(FileManagerActivity.this,fileManagerUtils.mAudioSize));
                    break;
                case R.id.rlayout_file_image://图像
                    intent.putExtra("title", "图像");
                    intent.putExtra("type", FileIconAndType.TYPE_IMAGE);
                    bundle.putSerializable("data",new ArrayList<FileInfo>(fileManagerUtils.mImageList));
                    intent.putExtra("size",Formatter.formatFileSize(FileManagerActivity.this,fileManagerUtils.mImageSize));
                    break;
                case R.id.rlayout_file_package://压缩包
                    intent.putExtra("title", "压缩包");
                    intent.putExtra("type", FileIconAndType.TYPE_ZIP);
                    bundle.putSerializable("data",new ArrayList<FileInfo>(fileManagerUtils.mZipList));
                    intent.putExtra("size",Formatter.formatFileSize(FileManagerActivity.this,fileManagerUtils.mZipSize));
                    break;
                case R.id.rlayout_file_software://软件包
                    intent.putExtra("title", "软件包");
                    intent.putExtra("type", FileIconAndType.TYPE_APK);
                    bundle.putSerializable("data",new ArrayList<FileInfo>(fileManagerUtils.mApkList));
                    intent.putExtra("size",Formatter.formatFileSize(FileManagerActivity.this,fileManagerUtils.mApkSize));
                    break;
            }
            if (intent != null){
                intent.putExtras(bundle);
                startActivity(intent);
            }



        }
    };


    /**
     * 监听按键
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            finish();
            if (from.contains("home")) {
                overridePendingTransition(R.anim.translate_t2b_enter,
                        R.anim.translate_t2b_exit);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
