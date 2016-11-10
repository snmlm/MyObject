package com.ds.interface1;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.ds.interface1.adapter.HomeListViewAdapter;
import com.ds.interface1.adapter.HomeViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private TextView mTvTopTitle;
    private ImageButton mIbTopTitleLeft;
    private ListView mLvHomeLv;
    private ViewPager mVpHomeVp;
    private static final int[] IMAGESLP = {R.drawable.dicar_forum_car_qing,
                                            R.drawable.dicar_forum_car_g6,
            R.drawable.dicar_forum_car_l3,
            R.drawable.dicar_forum_car_c5,
            R.drawable.dicar_forum_car_s6,
            R.drawable.dicar_forum_car_s7,
            R.drawable.dicar_forum_car_sirui};
    private static final int[] IMAGESVP = {R.mipmap.advertisement,R.mipmap.advertisement_one,
            R.mipmap.advertisement_two,R.mipmap.advertisement_three};
    private View mRidge;

    private void assignViews() {
        mTvTopTitle = (TextView) findViewById(R.id.tv_top_title);
        mIbTopTitleLeft = (ImageButton) findViewById(R.id.ib_top_title_left);
        mLvHomeLv = (ListView) findViewById(R.id.lv_home_lv);

        mRidge = View.inflate(MainActivity.this, R.layout.layout_hom_ridge, null);

        mVpHomeVp = (ViewPager) mRidge.findViewById(R.id.vp_home_vp);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
        mVpHomeVp.setAdapter(new HomeViewPagerAdapter(this,IMAGESVP));

        mLvHomeLv.setAdapter(new HomeListViewAdapter(this,IMAGESLP));
        mLvHomeLv.addHeaderView(mRidge);
    }
}
