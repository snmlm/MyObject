package com.ds.dialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private int i;
    private boolean tag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick1(View view) {
        i = 0;
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("正在下载");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
        progressDialog.setMax(100);
        progressDialog.show();

        new Timer(false).schedule(new TimerTask() {
            @Override
            public void run() {
                if(tag){
                    progressDialog.incrementProgressBy(-1);
                    if(progressDialog.getProgress() <= 0){
                        tag = false;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.setTitle("重新下载");
                            }
                        });
                    }
                }else{
                    progressDialog.incrementProgressBy(1);
                    if(progressDialog.getProgress() >= progressDialog.getMax()){
                        tag = true;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.setTitle("下载错误，正在上传");
                            }
                        });

                    }
                }

            }
        },0,50);
    }

    public void onClick2(View view) {
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("信息")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNeutralButton("中间", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setIcon(R.mipmap.ic_launcher)
                .show();
    }
}
