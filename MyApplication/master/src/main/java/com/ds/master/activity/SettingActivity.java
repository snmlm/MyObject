package com.ds.master.activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ds.master.R;
import com.ds.master.adapter.SettingListViewAdapter;
import com.ds.master.utils.FileUtils;
import com.ds.master.view.TitleBarView;

import java.io.File;

/**
 * 设置页面
 * Created by xxxxx on 2016/9/28.
 */
public class SettingActivity extends BaseActivity {

    /**
     * 标记哪里来的
     */
    private String from;
    private ListView mLvSettingList;
    private static final int[] TAGS = {0, 0, 0, 1, 1, 1, 1, 1};
    private static final String[] NAMES = {"开机启动", "通知图标", "消息推送",
            "帮助说明", "意见反馈", "好友分享", "版本更新", "关于我们"};
    /**
     * 设置对象
     */
    private static boolean[] mSetting = {false, false, false, false, false, false, false, false};
    private TitleBarView mTbvSettingTitle;

    private void assignViews() {
        mTbvSettingTitle = (TitleBarView) findViewById(R.id.tbv_setting_title);
        mLvSettingList = (ListView) findViewById(R.id.lv_setting_list);
        //从文件中获取设置的属性
        if (FileUtils.readInfo(SettingActivity.this) != null) {
            mSetting = FileUtils.readInfo(SettingActivity.this);
        }
    }


    @Override
    protected void initView() {
        setContentView(R.layout.activity_setting);
        assignViews();
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        from = intent.getStringExtra("from");
        mTbvSettingTitle.setTopTitleText(intent.getStringExtra("title"));
        //设置listview
        mLvSettingList.setAdapter(new SettingListViewAdapter(SettingActivity.this, TAGS, NAMES, mSetting));

        //setAdapter是异步设置，数据不同步，在主线程中获取listView中子布局，是获取不到的，所以要在异步的线程中去设置监听
        /*mLvSettingList.post(new Runnable() {
            @Override
            public void run() {
                //listview中的ToggleButton设置监听
                for (int i = 0;i<TAGS.length;i++){
                    if(TAGS[i] == 0){
                        ((ToggleButton)(mLvSettingList.getChildAt(i)).findViewById(R.id.tb_setting_set)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                //改变数据状态，写入数据
                                mSetting[i] =
                            }
                        });
                    }
                }
            }
        });*/
    }

    @Override
    protected void setListener() {
        //返回按钮监听
        mTbvSettingTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingActivity.this, "设置", Toast.LENGTH_SHORT).show();
                finish();
                overridePendingTransition(R.anim.translate_l2r_enter,
                        R.anim.translate_l2r_exit);
            }
        });

        mLvSettingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 3:

                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        //版本更新
                        upDateVersion();
                        break;
                    case 7:
                        break;
                }
            }
        });
    }

    /**
     * 版本更新
     */
    private void upDateVersion() {
        new AlertDialog.Builder(this)
                .setTitle("当前版本过低，需要更新")
                .setMessage("确定需要更新到最新版本吗？")
                .setNegativeButton("取消", null)
                .setPositiveButton("更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //版本更新
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.fromFile(
                                new File(Environment.getExternalStorageDirectory(), "master.apk")),
                                "application/vnd.android.package-archive");
                        startActivity(intent);

                    }
                }).show();
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
                overridePendingTransition(R.anim.translate_l2r_enter,
                        R.anim.translate_l2r_exit);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
