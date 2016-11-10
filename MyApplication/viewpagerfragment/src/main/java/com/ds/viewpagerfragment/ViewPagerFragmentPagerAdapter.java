package com.ds.viewpagerfragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Administrator on 2016/9/23.
 */
public class ViewPagerFragmentPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentPagerAdapterFactory.getFrament(position);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public long getItemId(int position) {
        if(position < 0){
            position = -position;
        }
        return position % FragmentPagerAdapterFactory.FRAGMENTS_COUNT;
    }
}
