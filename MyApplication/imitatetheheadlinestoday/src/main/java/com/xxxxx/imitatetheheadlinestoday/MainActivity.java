package com.xxxxx.imitatetheheadlinestoday;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private ViewPager mVpMainVp;
    private RadioGroup mRpMainRp;
    private RadioButton mRbMainHome;
    private RadioButton mRbMainDiscover;
    private RadioButton mRbMainCare;
    private RadioButton mRbMainMine;

    private void assignViews() {
        mVpMainVp = (ViewPager) findViewById(R.id.vp_main_vp);
        mRpMainRp = (RadioGroup) findViewById(R.id.rp_main_rp);
        mRbMainHome = (RadioButton) findViewById(R.id.rb_main_home);
        mRbMainDiscover = (RadioButton) findViewById(R.id.rb_main_discover);
        mRbMainCare = (RadioButton) findViewById(R.id.rb_main_care);
        mRbMainMine = (RadioButton) findViewById(R.id.rb_main_mine);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
        mVpMainVp.setAdapter(new MainFragmentPagerAdapter(this.getSupportFragmentManager()));
        mRpMainRp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_main_home:
                        mVpMainVp.setCurrentItem(0);
                        break;
                    case R.id.rb_main_discover:
                        mVpMainVp.setCurrentItem(1);
                        break;
                    case R.id.rb_main_care:
                        mVpMainVp.setCurrentItem(2);
                        break;
                    case R.id.rb_main_mine:
                        mVpMainVp.setCurrentItem(3);
                        break;
                }
            }
        });
        mVpMainVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mRpMainRp.check(R.id.rb_main_home);
                        break;
                    case 1:
                        mRpMainRp.check(R.id.rb_main_discover);
                        break;
                    case 2:
                        mRpMainRp.check(R.id.rb_main_care);
                        break;
                    case 3:
                        mRpMainRp.check(R.id.rb_main_mine);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
