package com.ds.viewpagerfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/9/23.
 */
public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmet_view,null);
        ((TextView)view.findViewById(R.id.tv_fragment_tv)).setText("首页");
        TabLayout mtl = (TabLayout) view.findViewById(R.id.tl_view_tl);
        mtl.addTab(mtl.newTab().setText("文1"));
        mtl.addTab(mtl.newTab().setText("文2"));
        mtl.addTab(mtl.newTab().setText("文3"));
        mtl.addTab(mtl.newTab().setText("文4"));
        mtl.addTab(mtl.newTab().setText("文5"));
        mtl.addTab(mtl.newTab().setText("文6"));
        mtl.addTab(mtl.newTab().setText("文7"));
        mtl.addTab(mtl.newTab().setText("文8"));
        mtl.addTab(mtl.newTab().setText("文9"));
        mtl.addTab(mtl.newTab().setText("文0"));
        return view;

    }
}
