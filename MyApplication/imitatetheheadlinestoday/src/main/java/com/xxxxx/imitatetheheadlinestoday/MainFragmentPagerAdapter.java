package com.xxxxx.imitatetheheadlinestoday;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

/**
 * Created by xxxxx on 2016/9/25.
 */
public class MainFragmentPagerAdapter extends FragmentPagerAdapter {


    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * 返回ViewPager的每一个item(每一个item是Fragment)
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        return MainFragmentFactory.getFragment(position);
    }

    /**
     * 返回ViewPager页的数量
     * @return
     */
    @Override
    public int getCount() {
        return MainFragmentFactory.FRAGMENT_COUNT;//4
    }

    /**
     * 销毁item
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
