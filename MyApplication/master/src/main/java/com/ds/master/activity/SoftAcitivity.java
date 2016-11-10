package com.ds.master.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import com.ds.master.R;
import com.ds.master.adapter.SoftListViewAdapter;
import com.ds.master.bean.AppInfo;
import com.ds.master.view.TitleBarView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/12.
 */
public class SoftAcitivity extends BaseActivity {
    @BindView(R.id.lv_soft_manage)
    ListView mLvSoftManage;
    @BindView(R.id.tbn_soft_title)
    TitleBarView mTbnSoftTitle;
    @BindView(R.id.cb_soft_all)
    CheckBox mCbSoftAll;
    @BindView(R.id.btn_soft_uninstall)
    Button mBtnSoftUninstall;
    private SoftListViewAdapter softListViewAdapter;
    public Handler handler;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_soft);
        ButterKnife.bind(this);
        softListViewAdapter = new SoftListViewAdapter(this,mCbSoftAll);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                mLvSoftManage.setAdapter(softListViewAdapter);
            }
        };
    }

    @Override
    protected void initData() {
        mTbnSoftTitle.setTopTitleText(getIntent().getStringExtra("title"));
        new Thread() {
            @Override
            public void run() {
                softListViewAdapter.setList(getIntent().getIntExtra("tag", 0));
                softListViewAdapter.notifyDataSetChanged();
                handler.sendEmptyMessage(0);

            }
        }.start();
    }

    @Override
    protected void setListener() {
        mTbnSoftTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                if (getIntent().getStringExtra("from").contains("softManage")) {
                    overridePendingTransition(R.anim.translate_t2b_enter,
                            R.anim.translate_t2b_exit);
                }
            }
        });
        mCbSoftAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(SoftListViewAdapter.isThis && !isChecked ){
                    SoftListViewAdapter.isThis = false;
                }else{
                    SoftListViewAdapter.isThis = false;
                    for (AppInfo appInfo : softListViewAdapter.getList()) {
                        appInfo.isClear = isChecked;
                    }
                    softListViewAdapter.notifyDataSetChanged();
                }
            }
        });

        mBtnSoftUninstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (AppInfo appInfo : softListViewAdapter.getList()) {
                    if(appInfo.isClear == true){
                        Intent intent = new Intent();
                        //Uri uri = Uri.parse("package:"+appInfo.appPackage);//获取删除包名的URI
                        intent.setAction(Intent.ACTION_DELETE);//设置我们要执行的卸载动作
                        intent.setData(Uri.parse("package:"+appInfo.appPackage));//设置获取到的URI
                        startActivity(intent);
                    }
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
            if (getIntent().getStringExtra("from").contains("softManage")) {
                overridePendingTransition(R.anim.translate_t2b_enter,
                        R.anim.translate_t2b_exit);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
