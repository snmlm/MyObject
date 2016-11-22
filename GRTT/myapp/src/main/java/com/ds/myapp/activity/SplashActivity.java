package com.ds.myapp.activity;

import android.content.Intent;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.ds.myapp.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/10/20.
 */
public class SplashActivity extends  BaseActivity {
    private ImageView mImgSplashShow;

    private void assignViews() {
        mImgSplashShow = (ImageView) findViewById(R.id.img_splash_show);
    }

    @Override
    public void init() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        setContentView(R.layout.activity_splash);
        assignViews();
        AlphaAnimation alphaAnimation = new AlphaAnimation(0f,1f);
        alphaAnimation.setDuration(3000);
        mImgSplashShow.setAnimation(alphaAnimation);

        new Timer(false).schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,HomeActivity.class));
                finish();
            }
        },4000);
    }
}
