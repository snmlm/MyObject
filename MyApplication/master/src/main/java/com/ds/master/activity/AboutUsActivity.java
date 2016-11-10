package com.ds.master.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.ds.master.R;
import com.ds.master.view.TitleBarView;

/**
 * 关于我们
 * Created by xxxxx on 2016/9/28.
 */
public class AboutUsActivity extends BaseActivity {

    /** 标记哪里来的 */
    private String from;
    private TitleBarView mTbvAboutTitle;

    private void assignViews() {
        mTbvAboutTitle = (TitleBarView) findViewById(R.id.tbv_about_title);
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
                Toast.makeText(AboutUsActivity.this, "关于界面", Toast.LENGTH_SHORT).show();
                finish();
                if(from.contains("home")){
                    overridePendingTransition(R.anim.translate_r2l_enter,
                            R.anim.translate_r2l_exit);
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
        if(event.getRepeatCount() == 0){
            switch (keyCode){
                case KeyEvent.KEYCODE_BACK:
                    finish();
                    if(from.contains("home")){
                        overridePendingTransition(R.anim.translate_r2l_enter,
                                R.anim.translate_r2l_exit);
                    }
                    return true;
                case KeyEvent.KEYCODE_HOME:
                    break;
                case KeyEvent.KEYCODE_MENU:
                    break;
            }
        }

      /*  if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            finish();
            if(from.contains("home")){
                overridePendingTransition(R.anim.translate_l2r_enter,
                        R.anim.translate_l2r_exit);
            }
            return true;
        }*/
        return super.onKeyDown(keyCode, event);
    }
}
