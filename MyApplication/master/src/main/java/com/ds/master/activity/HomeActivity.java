package com.ds.master.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ds.master.R;
import com.ds.master.utils.RAMUtils;
import com.ds.master.view.CreateView;

import java.util.TimerTask;

/**
 * 主页面
 * Created by xxxxx on 2016/9/27.
 */
public class HomeActivity extends BaseActivity {

    /**
     * 头部左侧按钮
     */
    private ImageButton mIbHomeTopLeft;
    /**
     * 头部右侧按钮
     */
    private ImageButton mIbHomeTopRight;

    /**
     * 自定义的圆
     */
    private CreateView mCvHomeCenterSpeed;
    /**
     * 图片 可以点击的
     */
    private ImageView mIvHomeCenterSpeed;
    /**
     * 显示的数值
     */
    private TextView mTvHomeCenterNumber;
    /**
     * 手机加速 可以点击的TextView
     */
    private TextView mTvHomeCenterSpeed;
    /**
     * 手机加速
     */
    private TextView mTvHomeBottomRocket;
    /**
     * 软件管理
     */
    private TextView mTvHomeBottomSoftmgr;
    /**
     * 手机管理
     */
    private TextView mTvHomeBottomPhonemgr;
    /**
     * 电话管理
     */
    private TextView mTvHomeBottomTelmgr;
    /**
     * 文件管理
     */
    private TextView mTvHomeBottomFilemgr;
    /**
     * sd清理
     */
    private TextView mTvHomeBottomSdclean;


    private void assignViews() {

        mIbHomeTopLeft = (ImageButton) findViewById(R.id.ib_home_top_left);
        mIbHomeTopRight = (ImageButton) findViewById(R.id.ib_home_top_right);

        mCvHomeCenterSpeed = (CreateView) findViewById(R.id.cv_home_center_speed);
        mIvHomeCenterSpeed = (ImageView) findViewById(R.id.iv_home_center_speed);
        mTvHomeCenterNumber = (TextView) findViewById(R.id.tv_home_center_number);
        mTvHomeCenterSpeed = (TextView) findViewById(R.id.tv_home_center_speed);


        mTvHomeBottomRocket = (TextView) findViewById(R.id.tv_home_bottom_rocket);
        mTvHomeBottomSoftmgr = (TextView) findViewById(R.id.tv_home_bottom_softmgr);
        mTvHomeBottomPhonemgr = (TextView) findViewById(R.id.tv_home_bottom_phonemgr);
        mTvHomeBottomTelmgr = (TextView) findViewById(R.id.tv_home_bottom_telmgr);
        mTvHomeBottomFilemgr = (TextView) findViewById(R.id.tv_home_bottom_filemgr);
        mTvHomeBottomSdclean = (TextView) findViewById(R.id.tv_home_bottom_sdclean);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_home);
        assignViews();
        //显示当前的百分比
        mCvHomeCenterSpeed.setSweepAngle(360 - ((int) (RAMUtils.getUsableRAMPercent(HomeActivity.this) * 360 + 0.5f)));
    }

    @Override
    protected void setListener() {

        //左上角图标
        mIbHomeTopLeft.setOnClickListener(listener);
        //右上角设置
        mIbHomeTopRight.setOnClickListener(listener);

        //设置图片点击
        mIvHomeCenterSpeed.setOnClickListener(listener);
        //设置手机加速点击事件
        mTvHomeCenterSpeed.setOnClickListener(listener);

        //手机加速
        mTvHomeBottomRocket.setOnClickListener(listener);
        //软件管理
        mTvHomeBottomSoftmgr.setOnClickListener(listener);
        //手机管理
        mTvHomeBottomPhonemgr.setOnClickListener(listener);
        //通讯大全
        mTvHomeBottomTelmgr.setOnClickListener(listener);
        //文件管理
        mTvHomeBottomFilemgr.setOnClickListener(listener);
        //垃圾清理
        mTvHomeBottomSdclean.setOnClickListener(listener);


        //设置监听 使数值和扫描的数值一样
        mCvHomeCenterSpeed.setOnUpDateListener(new CreateView.onUpDateListener() {
            @Override
            public void setOnUpDate(final int sweepAngle) {
                runOnUiThread(new TimerTask() {
                    @Override
                    public void run() {
                        mTvHomeCenterNumber.setText(String.format(getResources().
                                        getString(R.string.home_center_text_number),
                                (int) (sweepAngle / 360f * 100 + 0.5f)));
                    }
                });
            }
        });

    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.ib_home_top_left:
                    intent = new Intent(HomeActivity.this, AboutUsActivity.class);
                    intent.putExtra("title", "关于我们");
                    intent.putExtra("from", "home");
                    startActivity(intent);
                    overridePendingTransition(R.anim.translate_l2r_enter,
                            R.anim.translate_l2r_exit);
                    break;
                case R.id.ib_home_top_right:
                    intent = new Intent(HomeActivity.this, SettingActivity.class);
                    intent.putExtra("title", "设置");
                    intent.putExtra("from", "home");
                    startActivity(intent);
                    overridePendingTransition(R.anim.translate_r2l_enter,
                            R.anim.translate_r2l_exit);
                    break;
                case R.id.iv_home_center_speed:
                case R.id.tv_home_center_speed:
                    RAMUtils.clear(HomeActivity.this);//清理数据
                    mCvHomeCenterSpeed.setSweepAngle(360 - ((int) (RAMUtils.getUsableRAMPercent(HomeActivity.this) * 360 + 0.5f)));
                    break;
                case R.id.tv_home_bottom_rocket:
                    intent = new Intent(HomeActivity.this, RocketManagerActivity.class);
                    intent.putExtra("title", "手机加速");
                    intent.putExtra("from", "home");
                    startActivity(intent);
                    overridePendingTransition(R.anim.translate_b2t_enter,
                            R.anim.translate_b2t_exit);
                    break;
                case R.id.tv_home_bottom_softmgr:
                    intent = new Intent(HomeActivity.this, SoftManagerActivity.class);
                    intent.putExtra("title", "软件管理");
                    intent.putExtra("from", "home");
                    startActivity(intent);
                    overridePendingTransition(R.anim.translate_b2t_enter,
                            R.anim.translate_b2t_exit);
                    break;
                case R.id.tv_home_bottom_phonemgr:
                    intent = new Intent(HomeActivity.this, PhoneManagerActivity.class);
                    intent.putExtra("title", "手机管理");
                    intent.putExtra("from", "home");
                    startActivity(intent);
                    overridePendingTransition(R.anim.translate_b2t_enter,
                            R.anim.translate_b2t_exit);
                    break;
                case R.id.tv_home_bottom_telmgr:
                    intent = new Intent(HomeActivity.this, TelManagerActivity.class);
                    intent.putExtra("title", "通讯大全");
                    intent.putExtra("from", "home");
                    startActivity(intent);
                    overridePendingTransition(R.anim.translate_b2t_enter,
                            R.anim.translate_b2t_exit);
                    break;
                case R.id.tv_home_bottom_filemgr:
                    intent = new Intent(HomeActivity.this, FileManagerActivity.class);
                    intent.putExtra("title", "文件设置");
                    intent.putExtra("from", "home");
                    startActivity(intent);
                    overridePendingTransition(R.anim.translate_b2t_enter,
                            R.anim.translate_b2t_exit);
                    break;
                case R.id.tv_home_bottom_sdclean:
                    intent = new Intent(HomeActivity.this, SdCleanActivity.class);
                    intent.putExtra("title", "垃圾清理");
                    intent.putExtra("from", "home");
                    startActivity(intent);
                    overridePendingTransition(R.anim.translate_b2t_enter,
                            R.anim.translate_b2t_exit);
                    break;
            }
        }
    };


}
