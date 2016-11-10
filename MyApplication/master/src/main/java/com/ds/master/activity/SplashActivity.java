package com.ds.master.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.ds.master.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 闪屏页
 * Created by xxxxx on 2016/9/27.
 */
public class SplashActivity extends BaseActivity {

    private TextView mTvSplashSkip;

    private int time = 0;
    private Timer timer;

    private void assignViews() {
        mTvSplashSkip = (TextView) findViewById(R.id.tv_splash_skip);
    }

    @Override
    protected void initView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        assignViews();
        timer = new Timer(false);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new TimerTask() {
                    @Override
                    public void run() {
                        mTvSplashSkip.setText(String.format(getResources().getString(R.string.splash_skip),time--));
                        if(time < 0){
                            if(timer != null)timer.cancel();
                            startHomeActivity();

                        }
                    }
                });
            }
        },0,1000);
        //打印版本号
        Toast.makeText(this,getLocalVersions(), Toast.LENGTH_SHORT).show();
    }

    /**
     * 打开主页面
     */
    private void startHomeActivity(){
        startActivity(new Intent(SplashActivity.this,HomeActivity.class));
        overridePendingTransition(R.anim.alpha_splash_enter,R.anim.alpha_splash_exit);
        finish();
    }

    /**
     * 获取本地版本号
     * @return 版本号
     */
    private String getLocalVersions(){
        PackageManager mPackageManager = getPackageManager();
        try {
            PackageInfo packageInfo = mPackageManager.getPackageInfo(getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
}
