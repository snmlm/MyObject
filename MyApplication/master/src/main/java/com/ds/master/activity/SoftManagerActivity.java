package com.ds.master.activity;

import android.content.Intent;
import android.text.format.Formatter;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ds.master.R;
import com.ds.master.bean.AppInfo;
import com.ds.master.utils.ROMUtils;
import com.ds.master.utils.SDCardUtils;
import com.ds.master.view.CircleView;
import com.ds.master.view.TitleBarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 软件管理
 * Created by xxxxx on 2016/9/28.
 */
public class SoftManagerActivity extends BaseActivity {

    @BindView(R.id.tbn_soft_title)
    TitleBarView mTbnSoftTitle;
    @BindView(R.id.cv_soft_showphone)
    CircleView mCvSoftShowPhone;
    @BindView(R.id.cv_soft_showsd)
    CircleView mCvSoftShowsd;
    @BindView(R.id.img_soft_interior)
    ImageView mImgSoftInterior;
    @BindView(R.id.tv_soft_interior)
    TextView mTvSoftInterior;
    @BindView(R.id.img_soft_sd)
    ImageView mImgSoftSd;
    @BindView(R.id.tv_soft_sd)
    TextView mTvSoftSd;
    @BindView(R.id.pb_soft_interior)
    ProgressBar mPbSoftInterior;
    @BindView(R.id.tv_soft_interior_data)
    TextView mTvSoftInteriorData;
    @BindView(R.id.pb_soft_sd)
    ProgressBar mPbSoftSd;
    @BindView(R.id.tv_soft_sd_data)
    TextView mTvSoftSdData;
    @BindView(R.id.rlayout_soft_allsoft)
    RelativeLayout mRlayoutSoftAllsoft;
    @BindView(R.id.rlayout_soft_system)
    RelativeLayout mRlayoutSoftSystem;
    @BindView(R.id.rlayout_soft_user)
    RelativeLayout mRlayoutSoftUser;
    @BindView(R.id.tv_soft_interior_img_data)
    TextView mTvSoftInteriorImgData;
    @BindView(R.id.tv_soft_sd_img_data)
    TextView mTvSoftSdImgData;


    /**
     * 标记哪里来的
     */
    private String from;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_soft_manager);
        ButterKnife.bind(this);
    }


    @Override
    protected void initData() {
        Intent intent = getIntent();
        from = intent.getStringExtra("from");
        mTbnSoftTitle.setTopTitleText(intent.getStringExtra("title"));

        float dataROM = ROMUtils.getPerent();
        float dataSD = SDCardUtils.getPerent();
        mCvSoftShowPhone.setSweepAngle((int) ((1 - dataROM) * 360 + 0.5f));
        mCvSoftShowsd.setSweepAngle((int) ((1 - dataSD) * 360 + 0.5f));

        mTvSoftInteriorImgData.setText((int) ((1 - dataROM) * 100 + 0.5f) + "%");
        mTvSoftSdImgData.setText((int) ((1 - dataSD) * 100 + 0.5f)+"%");

        mPbSoftInterior.setProgress((int) ((1 - ROMUtils.getPerent()) * 100 + 0.5f));
        mPbSoftSd.setProgress((int) ((1 - SDCardUtils.getPerent()) * 100 + 0.5f));

        mTvSoftInteriorData.setText("可用："
                + Formatter.formatFileSize(this, ROMUtils.getAvailableCount())
                + "/"
                + Formatter.formatFileSize(this, ROMUtils.getCount()));
        mTvSoftSdData.setText("可用："
                + Formatter.formatFileSize(this, SDCardUtils.getAvailableCount())
                + "/"
                + Formatter.formatFileSize(this, SDCardUtils.getCount()));
       /* new Thread(){
            @Override
            public void run() {
                new Timer(false).schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });
                    }
                },0,20);
            }
        }.start();*/

    }


    @Override
    protected void setListener() {
        mTbnSoftTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SoftManagerActivity.this, "手机管理", Toast.LENGTH_SHORT).show();
                finish();
                if (from.contains("home")) {
                    overridePendingTransition(R.anim.translate_t2b_enter,
                            R.anim.translate_t2b_exit);
                }
            }
        });
    }

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

    @OnClick({R.id.rlayout_soft_allsoft, R.id.rlayout_soft_system, R.id.rlayout_soft_user})
    public void onClick(View view) {
        Intent intent = new Intent(SoftManagerActivity.this,SoftAcitivity.class);
        switch (view.getId()) {
            case R.id.rlayout_soft_allsoft:
                intent.putExtra("tag", AppInfo.ALL_TAG);
                intent.putExtra("title", "所有软件");
                break;
            case R.id.rlayout_soft_system:
                intent.putExtra("tag", AppInfo.SYSTEM_TAG);
                intent.putExtra("title", "系统软件");
                break;

            case R.id.rlayout_soft_user:
                intent.putExtra("tag", AppInfo.NOT_SYSTEM_TAG);
                intent.putExtra("title", "用户软件");
                break;
        }
        intent.putExtra("from","softManage");
        startActivity(intent);
    }
}
