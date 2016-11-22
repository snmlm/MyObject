package com.ds.myapp.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ds.myapp.fragment.news.HotFragment;
import com.ds.myapp.fragment.news.InternationalFragment;
import com.ds.myapp.fragment.news.MilitaryFragment;
import com.ds.myapp.fragment.news.PictureFragment;
import com.ds.myapp.fragment.news.RecommendFragment;
import com.ds.myapp.fragment.news.RecreationFragment;
import com.ds.myapp.fragment.news.SocietyFragment;
import com.ds.myapp.fragment.news.TechnologyFragment;

import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 */
public class NewsAdapater extends FragmentStatePagerAdapter {


    private List<String> list_title;

    public NewsAdapater(FragmentManager fm,List<String> list_title) {
        super(fm);
        this.list_title = list_title;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list_title.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new RecommendFragment();
                break;
            case 1:
                fragment = new HotFragment();
                break;
            case 2:
                fragment = new SocietyFragment();
                break;
            case 3:
                fragment = new TechnologyFragment();
                break;
            case 4:
                fragment = new RecreationFragment();
                break;
            case 5:
                fragment = new PictureFragment();
                break;
            case 6:
                fragment = new InternationalFragment();
                break;
            case 7:
                fragment = new MilitaryFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return list_title.size();
    }

}
