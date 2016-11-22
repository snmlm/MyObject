package com.ds.myapp.activity;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ds.myapp.R;
import com.ds.myapp.fragment.LeftFragment;
import com.ds.myapp.fragment.RightFragment;
import com.ds.myapp.fragment.menu.NewsFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * 主页面 显示
 * Created by xxxxx on 2016/10/20.
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener{

    private ImageView mImgTitleHomeLeft;
    private TextView mTvTitleHome;
    private ImageView mImgTitleHomeRight;
    private RelativeLayout mRlayoutHomeShow;
    private SlidingMenu menu;

    private void assignViews() {
        mImgTitleHomeLeft = (ImageView) findViewById(R.id.img_title_home_left);
        mTvTitleHome = (TextView) findViewById(R.id.tv_title_home);
        mImgTitleHomeRight = (ImageView) findViewById(R.id.img_title_home_right);
        mRlayoutHomeShow = (RelativeLayout) findViewById(R.id.rlayout_home_show);
    }

    @Override
    public void init() {
        setContentView(R.layout.activity_home);
        assignViews();

        menu = new SlidingMenu(this);



    }

    @Override
    public void initData() {
        menu.setMode(SlidingMenu.LEFT_RIGHT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setBehindOffsetRes(R.dimen.slidemenu_left);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);

        menu.setMenu(R.layout.layout_slidingmenu_left_fragment);
        menu.setSecondaryMenu(R.layout.layout_slidingmenu_right_fragment);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.llayout_left_menu, new LeftFragment()).commit();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.llayout_right_menu, new RightFragment()).commit();
        showFragment(new NewsFragment());

    }

    @Override
    public void setListener() {
        mImgTitleHomeLeft.setOnClickListener(this);
        mImgTitleHomeRight.setOnClickListener(this);
    }


    public void setTitle(){

    }

    public void showFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rlayout_home_show, fragment).commit();
        menu.showContent();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_title_home_left:
                menu.showMenu();
                break;
            case R.id.img_title_home_right:
                menu.showSecondaryMenu();
                break;
        }
    }
}
