package com.ds.autoviewpager;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ViewPager mVpMainVp;
    private static final int[] IMAGES = {R.mipmap.dicar_forum_car_c5,
            R.mipmap.dicar_forum_car_g6,
            R.mipmap.dicar_forum_car_l3,
            R.mipmap.dicar_forum_car_qing,
            R.mipmap.dicar_forum_car_s6,
            R.mipmap.dicar_forum_car_s7,
            R.mipmap.dicar_forum_car_sirui};
    private static final int COUNT = 2*IMAGES.length;
    private int position;

    private void assignViews() {
        mVpMainVp = (ViewPager) findViewById(R.id.vp_main_vp);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
        mVpMainVp.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return COUNT;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View)object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                position %= IMAGES.length;
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setBackgroundResource(IMAGES[position]);
                container.addView(imageView);
                return imageView;
            }

            /**
             * 更新即将完成
             * @param container
             */
            @Override
            public void finishUpdate(ViewGroup container) {
                position = mVpMainVp.getCurrentItem();
                if(position == 0){
                    position += IMAGES.length;
                    mVpMainVp.setCurrentItem(position,false);
                }else if(position == COUNT - 1){
                    position -= IMAGES.length;
                    mVpMainVp.setCurrentItem(position,false);
                }
            }

        });


        new Timer(false).schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mVpMainVp.setCurrentItem(position++);
                    }
                });
            }
        },0,2000);
    }
}
