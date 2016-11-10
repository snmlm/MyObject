package com.ds.radiobutton;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ViewPager mVpMainVp;
    private RadioGroup mRgBottomRg;
    private RadioButton mRbBotomHome;
    private RadioButton mRbBotomDiscover;
    private RadioButton mRbBotomCare;
    private RadioButton mRbBotomMine;
    private NewPagerAdaper newPagerAdaper;

    private void assignViews() {
        mVpMainVp = (ViewPager) findViewById(R.id.vp_main_vp);
        mRgBottomRg = (RadioGroup) findViewById(R.id.rg_bottom_rg);
        mRbBotomHome = (RadioButton) findViewById(R.id.rb_botom_home);
        mRbBotomDiscover = (RadioButton) findViewById(R.id.rb_botom_discover);
        mRbBotomCare = (RadioButton) findViewById(R.id.rb_botom_care);
        mRbBotomMine = (RadioButton) findViewById(R.id.rb_botom_mine);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
        final ArrayList<Integer> images = new ArrayList<>();
        images.add(R.mipmap.bd);
        images.add(R.mipmap.small);
        images.add(R.mipmap.welcome);
        images.add(R.mipmap.wy);
        newPagerAdaper = new NewPagerAdaper(MainActivity.this, images);
        //mVpMainVp.removeView(View.inflate(MainActivity.this,R.layout.viewpager_layout,null));
        mVpMainVp.setPageTransformer(true, new MyPagerTransformer());
        mVpMainVp.setAdapter(newPagerAdaper);
        mVpMainVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            private int id;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Toast.makeText(MainActivity.this,position+"",Toast.LENGTH_SHORT).show();
            }

            /**
             *  当页面被选中的时候调用 第一次不会被调用
             * @param position 当前展示的item的位置
             */
            @Override
            public void onPageSelected(int position) {
                id = 0;
                switch (position){
                    case 0:
                        id = R.id.rb_botom_home;
                        break;
                    case 1:
                        id = R.id.rb_botom_discover;
                        break;
                    case 2:
                        id = R.id.rb_botom_care;
                        break;
                    case 3:
                        id = R.id.rb_botom_mine;
                        break;
                }
                runOnUiThread(new TimerTask() {
                    @Override
                    public void run() {
                        mRgBottomRg.check(id);
                    }
                });

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mRgBottomRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_botom_home:
                        mVpMainVp.setCurrentItem(0);
                        break;
                    case R.id.rb_botom_discover:
                        mVpMainVp.setCurrentItem(1);
                        break;
                    case R.id.rb_botom_care:
                        mVpMainVp.setCurrentItem(2);
                        break;
                    case R.id.rb_botom_mine:
                        mVpMainVp.setCurrentItem(3);
                        break;
                }
            }
        });

    }
}
