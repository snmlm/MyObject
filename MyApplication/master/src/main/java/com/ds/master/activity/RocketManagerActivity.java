package com.ds.master.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.format.Formatter;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ds.master.R;
import com.ds.master.adapter.RocketListViewAdapter;
import com.ds.master.bean.RunAppInfo;
import com.ds.master.utils.RAMUtils;
import com.ds.master.view.TitleBarView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 手机加速
 * Created by xxxxx on 2016/9/28.
 */
public class RocketManagerActivity extends BaseActivity {
    /** 顶部 */
    @BindView(R.id.tbn_rocketmnager_title)
    TitleBarView mTbnRocketmnagerTitle;
    /** 手机型号 */
    @BindView(R.id.tv_rocket_phone_name)
    TextView mTvRocketPhoneName;
    /** 系统型号 */
    @BindView(R.id.tv_rocket_system_name)
    TextView mTvRocketSystemName;
    /** 内存使用进度条 */
    @BindView(R.id.pb_rocket_RAM)
    ProgressBar mPbRocketRAM;
    /** 文字显示内存使用进度 */
    @BindView(R.id.tv_rocket_RAM)
    TextView mTvRocketRAM;
    /** 一键清理 */
    @BindView(R.id.btn_rocket_clean)
    Button mBtnRocketClean;
    /** 进程列表 */
    @BindView(R.id.lv_rocket_lv)
    ListView mLvRocketLv;
    /** 进程刷新 */
    @BindView(R.id.pb_rocket_flush)
    ProgressBar mPbRocketFlush;
    /** 选着框 */
    @BindView(R.id.cb_rocket_allselect)
    CheckBox mCbRocketAllselect;
    /** 显示当前进程/系统进程 */
    @BindView(R.id.btn_rocket_show)
    Button mBtnRocketShow;
    /**
     * 标记哪里来的
     */
    private String from;

    private Message message;


    private Handler handler ;

    private RocketListViewAdapter rocketListViewAdapter;

    private boolean isSystem = false;
    private boolean isAllselect = false;

    private boolean isThisCheck = true;
    private boolean isThatCheck = false;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_rocket_manager);
        ButterKnife.bind(this);
        new Thread(){
            @Override
            public void run() {
                rocketListViewAdapter = new RocketListViewAdapter(RocketManagerActivity.this,mCbRocketAllselect);
                rocketListViewAdapter.setList(false);
                rocketListViewAdapter.notifyDataSetChanged();
                handler.sendEmptyMessage(1);
            }
        }.start();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        mLvRocketLv.setAdapter(rocketListViewAdapter);
                        break;
                    case 2:
                        if(isThatCheck){
                            isThisCheck = false;
                        }
                        mCbRocketAllselect.setChecked(isAllselect);
                        isThisCheck = true;
                        break;
                    case 3:
                        if(isSystem){
                            mBtnRocketShow.setText("显示用户进程");
                        }else{
                            mBtnRocketShow.setText("显示系统进程");
                        }
                        mCbRocketAllselect.setChecked(false);
                        break;
                    case 4:
                        mLvRocketLv.setVisibility(View.VISIBLE);
                        mPbRocketFlush.setVisibility(View.GONE);
                        break;
                }

            }
        };
    }


    @Override
    protected void initData() {
        Intent intent = getIntent();
        from = intent.getStringExtra("from");
        mTbnRocketmnagerTitle.setTopTitleText(intent.getStringExtra("title"));

        /*手机品牌*/
        mTvRocketPhoneName.setText(Build.BRAND);
        /*手机版本*/
        mTvRocketSystemName.setText("Android "+Build.VERSION.RELEASE);


        /*进度条*/
        mPbRocketRAM.setProgress( (int)((1 - RAMUtils.getUsableRAMPercent(this))*100 + 0.5f));
        /*可用内存*/
        mTvRocketRAM.setText("已用内存："+ Formatter.formatFileSize(this,(RAMUtils.getTotalRAM() - RAMUtils.getUsableRAM(this)))
                +"/"+Formatter.formatFileSize(this,RAMUtils.getTotalRAM()));

        mBtnRocketShow.setText("显示用户进程");
    }


    @Override
    protected void setListener() {
        mTbnRocketmnagerTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RocketManagerActivity.this, "手机管理", Toast.LENGTH_SHORT).show();
                finish();
                if (from.contains("home")) {
                    overridePendingTransition(R.anim.translate_t2b_enter,
                            R.anim.translate_t2b_exit);
                }
            }
        });
        mCbRocketAllselect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(RocketListViewAdapter.isCheck && !isChecked ){
                    RocketListViewAdapter.isCheck = false;
                }else{
                    RocketListViewAdapter.isCheck = false;
                    for (RunAppInfo runAppInfo : rocketListViewAdapter.getList()) {
                        runAppInfo.isClear = isChecked;
                    }
                    rocketListViewAdapter.notifyDataSetChanged();
                }

            }
        });
    }

    @OnClick({R.id.btn_rocket_clean,R.id.btn_rocket_show})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_rocket_clean:
                RAMUtils.clearById(RocketManagerActivity.this,rocketListViewAdapter.getList());
                mLvRocketLv.setVisibility(View.GONE);
                mPbRocketFlush.setVisibility(View.VISIBLE);
                new Timer(false).schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(4);
                    }
                },2000);
               // Toast.makeText(RocketManagerActivity.this, "清理", Toast.LENGTH_SHORT).show();
                //更新 进度条 进度文字 动态修改？
                /*进度条*/
                mPbRocketRAM.setProgress( (int)((1 - RAMUtils.getUsableRAMPercent(this))*100 + 0.5f));
                /*可用内存*/
                mTvRocketRAM.setText("已用内存："+
                        Formatter.formatFileSize(this,(RAMUtils.getTotalRAM() - RAMUtils.getUsableRAM(this)))
                        +"/"+Formatter.formatFileSize(this,RAMUtils.getTotalRAM()));

                rocketListViewAdapter.setList(isSystem);
                rocketListViewAdapter.notifyDataSetChanged();

                break;
            case R.id.btn_rocket_show:
                handler.sendEmptyMessage(3);
                isSystem = !isSystem;
                rocketListViewAdapter.setList(isSystem);
                rocketListViewAdapter.notifyDataSetChanged();
                break;
        }
    }

    //private void

    /**
     * 监听按键
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
