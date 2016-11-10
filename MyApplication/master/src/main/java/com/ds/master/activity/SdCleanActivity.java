package com.ds.master.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.ds.master.R;
import com.ds.master.view.TitleBarView;

/**
 * sd清理
 * Created by xxxxx on 2016/9/28.
 */
public class SdCleanActivity extends BaseActivity{
    /** 标记哪里来的 */
    private String from;
    private TitleBarView mTbvAboutTitle;

    private void assignViews() {
        mTbvAboutTitle = (TitleBarView) findViewById(R.id.tbv_setting_title);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_aboutus);
        assignViews();
    }


    @Override
    protected void initData() {
        Intent intent = getIntent();
        from = intent.getStringExtra("from");
        mTbvAboutTitle.setTopTitleText(intent.getStringExtra("title"));
    }


    @Override
    protected void setListener() {
        mTbvAboutTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SdCleanActivity.this, "手机管理", Toast.LENGTH_SHORT).show();
                finish();
                if(from.contains("home")){
                    overridePendingTransition(R.anim.translate_t2b_enter,
                            R.anim.translate_t2b_exit);
                }

            }
        });
    }

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
            if(from.contains("home")){
                overridePendingTransition(R.anim.translate_t2b_enter,
                        R.anim.translate_t2b_exit);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
