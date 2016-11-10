package com.xxxxx.imitatetheheadlinestoday;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xxxxx on 2016/9/25.
 */
public class HomeFragment extends Fragment {
    private static final String[] NAMES = {"推荐","热点","视频","天津","社会",
                                           "订阅","娱乐","图片","科技","汽车"};
    private TabLayout mTlMineTl;
    private ViewPager mVpMineVp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        mTlMineTl = (TabLayout) view.findViewById(R.id.tl_mine_tl);
        mVpMineVp = (ViewPager) view.findViewById(R.id.vp_mine_vp);

        /*for (String name : NAMES) {
            mTlMineTl.addTab(mTlMineTl.newTab().setText(name));
        }*/
        mVpMineVp.setAdapter(new HomeFragmentPagerAdapter(getFragmentManager(),NAMES));
        mTlMineTl.setupWithViewPager(mVpMineVp);

        return view;
    }

}
