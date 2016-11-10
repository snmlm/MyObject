package com.ds.viewpagerfragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ViewPager mVpMainVp;

    private void assignViews() {
        mVpMainVp = (ViewPager) findViewById(R.id.vp_main_vp);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
        mVpMainVp.setAdapter(new ViewPagerFragmentPagerAdapter(getSupportFragmentManager()));
    }
}
