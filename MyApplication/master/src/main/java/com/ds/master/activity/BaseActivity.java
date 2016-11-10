package com.ds.master.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 基本activity 自己封装
 * Created by xxxxx on 2016/9/27.
 */
public abstract class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        setListener();
    }

    /**
     * 设置监听
     */
    protected  void setListener(){}

    /**
     * 初始化数据
     */
    protected  void initData(){}

    /**
     * 初始化视图
     */
    protected abstract void initView();
}
