package com.ds.master.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Camera;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ds.master.R;
import com.ds.master.utils.CPUInfo;
import com.ds.master.utils.RAMUtils;
import com.ds.master.utils.RootUtils;
import com.ds.master.view.TitleBarView;

import java.lang.reflect.Method;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 手机管理
 * Created by xxxxx on 2016/9/28.
 */
public class PhoneManagerActivity extends BaseActivity {
    /**
     * title
     */
    @BindView(R.id.tbn_phone_title)
    TitleBarView mTbnPhoneTitle;
    /**
     * 电池百分比 进度条
     */
    @BindView(R.id.pb_phone_cell)
    ProgressBar mPbPhoneCell;
    /**
     * 电池百分比 文字
     */
    @BindView(R.id.tv_phone_cell)
    TextView mTvPhoneCell;
    /**
     * 电池布局，用于点击
     */
    @BindView(R.id.llayout_phone_cell)
    LinearLayout mLlayoutPhoneCell;
    /**
     * 设备名称
     */
    @BindView(R.id.tv_phone_device_name)
    TextView mTvPhoneDeviceName;
    /**
     * 系统版本
     */
    @BindView(R.id.tv_phone_device_system)
    TextView mTvPhoneDeviceSystem;
    /**
     * 全部RAM
     */
    @BindView(R.id.tv_phone_RAM_count)
    TextView mTvPhoneRAMCount;
    /**
     * 剩余RAM
     */
    @BindView(R.id.tv_phone_RAM_available)
    TextView mTvPhoneRAMAvailable;
    /**
     * cup名称
     */
    @BindView(R.id.tv_phone_CPU_name)
    TextView mTvPhoneCPUName;
    /**
     * cup数量
     */
    @BindView(R.id.tv_phone_CPU_number)
    TextView mTvPhoneCPUNumber;
    /**
     * 手机分辨率
     */
    @BindView(R.id.tv_phone_camera_resolution)
    TextView mTvPhoneCameraResolution;
    /**
     * 照相机分辨率
     */
    @BindView(R.id.tv_phone_camera_phone_resolution)
    TextView mTvPhoneCameraPhoneResolution;
    /**
     * 基带版本
     */
    @BindView(R.id.tv_phone_system_version)
    TextView mTvPhoneSystemVersion;
    /**
     * 是否root
     */
    @BindView(R.id.tv_phone_system_isroot)
    TextView mTvPhoneSystemIsroot;

    /**
     * 标记哪里来的
     */
    private String from;
    private int mCellPercent;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_phone_manager);
        ButterKnife.bind(this);

        //接受电池广播
        checkPower();
    }


    @Override
    protected void initData() {
        Intent intent = getIntent();
        from = intent.getStringExtra("from");
        mTbnPhoneTitle.setTopTitleText(intent.getStringExtra("title"));

        //设备名称
        mTvPhoneDeviceName.setText("设备名称：" + Build.BRAND);
        //系统版本
        mTvPhoneDeviceSystem.setText("系统版本：Android " + Build.VERSION.RELEASE);

        //全部RAM
        mTvPhoneRAMCount.setText("全部运行内存：" + Formatter.formatFileSize(this, RAMUtils.getTotalRAM()));
        //剩余RAM
        mTvPhoneRAMAvailable.setText("剩余运行内存：" + Formatter.formatFileSize(this, RAMUtils.getUsableRAM(this)));

        //cup名称
        mTvPhoneCPUName.setText("cup名称：" + CPUInfo.getCpuName());
        //cup数量
        mTvPhoneCPUNumber.setText("cup数量：" + CPUInfo.getCpuNumber());

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        //手机分辨率
        mTvPhoneCameraResolution.setText("手机分辨率："
                + displayMetrics.widthPixels + " * " + displayMetrics.heightPixels);

        Camera camera = Camera.open();
        List<Camera.Size> supportedPreviewSizes = camera.getParameters().getSupportedPreviewSizes();
        Camera.Size size = supportedPreviewSizes.get(0);//最大像素
        //相机分辨率
        mTvPhoneCameraPhoneResolution.setText("相机最大分辨率：" + size.width + " * " + size.height);

        //记得释放  不释放下次进不来，camera被占用
        camera.stopPreview();
        camera.release();
        camera = null;


        //基带版本
        mTvPhoneSystemVersion.setText("基带版本：" + getBasebandVersion());
        //是否root
        mTvPhoneSystemIsroot.setText("是否root："+ (RootUtils.isDeviceRooted()? "是":"否"));
    }


    @Override
    protected void setListener() {
        mTbnPhoneTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PhoneManagerActivity.this, "手机检查", Toast.LENGTH_SHORT).show();
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

    @OnClick(R.id.llayout_phone_cell)
    public void onClick() {
        View view = View.inflate(PhoneManagerActivity.this,R.layout.dialog_phone_cell, null);
        ((TextView) view.findViewById(R.id.tv_dialog_phone_cell)).setText("当前电量："+mCellPercent+"%");
        ((TextView)view.findViewById(R.id.tv_dialog_phone_temperature)).setText("当前温度：0");
        new AlertDialog.Builder(PhoneManagerActivity.this)
                .setTitle("电池信息")
                .setView(view)
                .show();
    }

    public static final int MSG_BATT = 100;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //接受电池的信息，更新UI
            if (msg.what == MSG_BATT) {
                Intent intent = (Intent) msg.obj;
                //int rawlevel = intent.getIntExtra("level", -1);// 获得当前电量
                //int scale = intent.getIntExtra("scale", -1);//获得总电量
                mCellPercent = (int) (intent.getIntExtra("level", -1)
                        * 1.0f / intent.getIntExtra("scale", -1) * 100 + 0.5f);
                mPbPhoneCell.setProgress(mCellPercent);
                mTvPhoneCell.setText(mCellPercent + "%");
            }
        }

    };

    /**
     * 检查电量
     */
    public void checkPower() {
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                context.unregisterReceiver(this);
                handler.removeMessages(MSG_BATT);
                handler.obtainMessage(MSG_BATT, intent).sendToTarget();
            }
        }, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        handler.sendEmptyMessageDelayed(MSG_BATT, 1000);
    }

    /**
     * 获取手机基带版本
     * @return
     */
    public String getBasebandVersion() {
        try {
            Class cl = Class.forName("android.os.SystemProperties");
            Object invoker = cl.newInstance();
            Method m = cl.getMethod("get", new Class[]{String.class, String.class});
            Object result = m.invoke(invoker, new Object[]{"gsm.version.baseband", "no message"});
            return (String) result;
        } catch (Exception e) {}
        return null;
    }

}
