package com.ds.viewpagerfragment;


import android.support.v4.app.Fragment;

/**
 * Created by Administrator on 2016/9/23.
 */
public class FragmentPagerAdapterFactory {

    public final static  int FRAGMENTS_COUNT = 4;

    private static Fragment[] fragments = new Fragment[FRAGMENTS_COUNT];

    public static Fragment getFrament(int position){
        Fragment fragment = fragments[position % FRAGMENTS_COUNT];
        if(fragment == null){
            switch (position){
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new DiscoverFragment();
                    break;
                case 2:
                    fragment = new CareFragment();
                    break;
                case 3:
                    fragment = new MineFragment();
                    break;
            }
            fragments[position] = fragment;
        }
        return fragment;
    }
}
