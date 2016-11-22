package com.ds.myapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.ds.myapp.R;
import com.ds.myapp.adapter.GuideViwePagerAdapter;
import com.ds.myapp.utils.DensityUtils;

import cn.jpush.android.api.JPushInterface;

/**
 * 新手导航页
 *
 * Created by xxxxx on 2016/10/20.
 */
public class GuideActivity  extends  BaseActivity{
    /**
     * 显示
     */
    private ViewPager mVpGuideShow;
    /**
     * 图片
     */
    private int[] mImages;
    /**
     * 下部 布局
     */
    private LinearLayout mLinearLayout;

    /**
     * 是否是第一次打开
     */
    private boolean isFristOpen = true;
    /**
     * 存储
     */
    private SharedPreferences sp;
    /**
     * 存放位置
     */
    private String PATH;
    /**
     * 是否是第一次打开 字符串
     */
    private static final String IS_FRIST_OPEN = "isFristOpen";

    private void assignViews() {
        mVpGuideShow = (ViewPager) findViewById(R.id.vp_guide_show);
        mLinearLayout = (LinearLayout) findViewById(R.id.llayout_guide_point);
    }

    @Override
    public void init() {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(getApplicationContext());
        PATH = getResources().getString(R.string.app_config);
        sp = getSharedPreferences(PATH, MODE_PRIVATE);
        isFristOpen = sp.getBoolean(IS_FRIST_OPEN, true);
        if(isFristOpen){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
            setContentView(R.layout.acitviyt_guide);
            assignViews();
        }else{
            startActivity(new Intent(GuideActivity.this,SplashActivity.class));
            finish();
        }
    }

    @Override
    public void initData() {
        if(isFristOpen){
            //获取图片
            TypedArray typedArray = getResources().obtainTypedArray(R.array.GuideImages);
            mImages = new int[typedArray.length()];
            for (int i = 0; i < typedArray.length(); i++)
                mImages[i] = typedArray.getResourceId(i, -1);


            //设置显示ViewPager
            mVpGuideShow.setAdapter(new GuideViwePagerAdapter(GuideActivity.this, mImages));


            //设置居中
            mLinearLayout.setGravity(Gravity.CENTER);
            //向下部布局填加点
            for (int i = 0; i < mImages.length; i++) {
                View point = new View(GuideActivity.this);
                point.setBackgroundResource(R.drawable.select_guide_point);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        (int) (DensityUtils.px2dp(GuideActivity.this, 20f) + 0.5f),
                        (int) (DensityUtils.px2dp(GuideActivity.this, 20f) + 0.5f));
                if (i != 0) {
                    layoutParams.leftMargin = (int) (DensityUtils.px2dp(GuideActivity.this, 20f));
                } else {
                    point.setSelected(true);
                }

                point.setLayoutParams(layoutParams);
                mLinearLayout.addView(point);
            }
        }
    }

    @Override
    public void setListener() {
        if (isFristOpen) {
            mVpGuideShow.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    for (int i = 0; i < mLinearLayout.getChildCount(); i++) {
                        mLinearLayout.getChildAt(i).setSelected(i == position);
                    }
                    if(position == mLinearLayout.getChildCount()-1){
                        openSplashActivity();
                    }

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }


    private void openSplashActivity(){
        sp.edit().putBoolean("isFristOpen",false).commit();
        startActivity(new Intent(GuideActivity.this,SplashActivity.class));
        overridePendingTransition(R.anim.set_splash_enter,R.anim.set_guide_exit);
        finish();
    }
}
