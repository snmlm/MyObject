package com.ds.master.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ds.master.R;
import com.ds.master.adapter.GuideViewPagerAdapter;
import com.ds.master.bean.SingleNotification;
import com.ds.master.utils.DensityUtils;
import com.ds.master.utils.FileUtils;

/**
 * 新手指导
 * Created by xxxxx on 2016/9/27.
 */
public class GuideActivity extends BaseActivity{
    /** 主要显示的图片 */
    private ViewPager mVpGuideMain;
    /** 下方点的父布局 */
    private LinearLayout mLlayoutGuidePointParent;
    /** 跳过 */
    private TextView mTvGuideSkip;
    /** 进入闪屏页 */
    private Button mBtnGuideStartSplash;
    /** 页面显示的图片 */
    private static final int[] IMAGES = {R.mipmap.c,R.mipmap.d,R.mipmap.p};
    /** 是否第一次运行标志 */
    private static final String IS_FIRST_RUN = "isFirstRun";

    private boolean mIsFirstRun = true;
    private SharedPreferences sp;


    /**
     * 页面变量
     * 设置加载页
     */
    private void assignViews() {
        setContentView(R.layout.activity_gudie);
        mVpGuideMain = (ViewPager) findViewById(R.id.vp_guide_main);
        mLlayoutGuidePointParent = (LinearLayout) findViewById(R.id.llayout_guide_point_parent);
        mTvGuideSkip = (TextView) findViewById(R.id.tv_guide_skip);
        mBtnGuideStartSplash = (Button)findViewById(R.id.btn_guide_start_splash);
    }


    /**
     * 设置监听
     */
    @Override
    protected void setListener() {
        if(mIsFirstRun){
            mTvGuideSkip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startSplashActivity();
                }
            });
            mBtnGuideStartSplash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startSplashActivity();
                }
            });
            mVpGuideMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

                @Override
                public void onPageSelected(int position) {
                    for (int i = 0; i < IMAGES.length; i++) {
                        mLlayoutGuidePointParent.getChildAt(i).setSelected(i==position);//设置选中
                        if(position == (IMAGES.length-1)){
                            mTvGuideSkip.setVisibility(View.GONE);
                            mBtnGuideStartSplash.setVisibility(View.VISIBLE);
                        }else {
                            mTvGuideSkip.setVisibility(View.VISIBLE);
                            mBtnGuideStartSplash.setVisibility(View.GONE);
                        }
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {}
            });

        }


    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        if(mIsFirstRun){
            for (int i = 0; i < IMAGES.length; i++) {
                //创建视图
                View view = new View(this);
                view.setBackgroundResource(R.drawable.select_guide_point);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(DensityUtils.dp2px(this,10), DensityUtils.dp2px(this,10));
                if(i != 0)layoutParams.leftMargin = DensityUtils.dp2px(this,10);//除了第一项  左侧margin
                if(i == 0)view.setSelected(true); //第一个选中
                view.setLayoutParams(layoutParams);
                //填加视图
                mLlayoutGuidePointParent.addView(view);
            }
        }


    }

    /**
     * 初始化页面
     */
    @Override
    protected void initView() {
        sp = getSharedPreferences("guide_config", MODE_PRIVATE);
        mIsFirstRun = sp.getBoolean(IS_FIRST_RUN, true);
        if(mIsFirstRun){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
            assignViews();
            mVpGuideMain.setAdapter(new GuideViewPagerAdapter(this,IMAGES));
        }else{
            startSplashActivity();
        }

        boolean[] infos = FileUtils.readInfo(this);
        if(infos!=null){
            if(infos[1]){
                SingleNotification.newSingleNotification(this).openNotification();
            }
        }
    }

    /**
     * 打开闪屏页
     */
    private void startSplashActivity(){
        sp.edit().putBoolean(IS_FIRST_RUN,false).commit();//设置并提交
        startActivity(new Intent(GuideActivity.this,SplashActivity.class));
        finish();
    }
}
