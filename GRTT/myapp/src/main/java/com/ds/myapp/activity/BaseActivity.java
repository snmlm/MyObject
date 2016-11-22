package com.ds.myapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ds.myapp.R;

/**
 * acitivity基础类
 * Created by xxxxx on 2016/10/20.
 */
public class BaseActivity extends AppCompatActivity {

    // 默认是日间模式
    public int theme = R.style.AppTheme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 判断是否有主题存储
        if(savedInstanceState != null){
            theme = savedInstanceState.getInt("theme");
            setTheme(theme);
        }
        init();
        initData();
        setListener();
    }

    public void init(){}
    public void initData(){}
    public void setListener(){}

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("theme", theme);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        theme = savedInstanceState.getInt("theme");
    }
}
