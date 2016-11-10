package com.xxxxx.imitatetheheadlinestoday;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

/**
 * Created by xxxxx on 2016/9/25.
 */
public class HomeFragmentPagerAdapter extends FragmentPagerAdapter{

    private String[] names;

    public HomeFragmentPagerAdapter(FragmentManager fm, String[] names) {
        super(fm);
        this.names = names;
    }

    @Override
    public Fragment getItem(int position) {
      /*  HomeFragmentFragmentItem homeFragmentFragmentItem = new HomeFragmentFragmentItem();
        homeFragmentFragmentItem.setName(names[position]);*/
        return HomeFragmentFragmentItem.newInstace(position);
    }

    @Override
    public int getCount() {
        return names.length;
    }


    /**
     * 返回标题
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return names[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
