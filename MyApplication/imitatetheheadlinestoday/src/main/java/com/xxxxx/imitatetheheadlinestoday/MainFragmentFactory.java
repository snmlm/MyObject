package com.xxxxx.imitatetheheadlinestoday;

import android.support.v4.app.Fragment;

/**
 * Created by xxxxx on 2016/9/25.
 */
public class MainFragmentFactory {
    /** Fragment的数量 */
    public static final int FRAGMENT_COUNT = 4;
    /** 存放Fragment的集合*/
    private static Fragment[] fragments = new Fragment[FRAGMENT_COUNT];

    public static Fragment getFragment(int position){
        Fragment fragment = fragments[position];
        if(fragment == null){//集合中没有可以复用的
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
            fragments[position] = fragment;//将创建的Fragment保存到集合中，下次复用
        }
        return fragment;
    }
}
