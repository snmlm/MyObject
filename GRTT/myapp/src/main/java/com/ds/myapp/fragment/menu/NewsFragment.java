package com.ds.myapp.fragment.menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ds.myapp.R;
import com.ds.myapp.adapter.NewsAdapater;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/1.
 */

public class NewsFragment extends Fragment {


    private TabLayout mTlayoutNewsTitle;
    private ViewPager mVpNewsShow;

    private void assignViews(View view) {
        mTlayoutNewsTitle = (TabLayout) view.findViewById(R.id.tlayout_news_title);
        mVpNewsShow = (ViewPager) view.findViewById(R.id.vp_news_show);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_news, container, false);
        assignViews(view);

        mTlayoutNewsTitle.setTabMode(TabLayout.MODE_SCROLLABLE);
        List<String> list_title = new ArrayList<>();
        list_title.add("推荐");
        list_title.add("热点");
        list_title.add("社会");
        list_title.add("科技");
        list_title.add("娱乐");
        list_title.add("图片");
        list_title.add("国际");
        list_title.add("军事");

        mVpNewsShow.setAdapter(new NewsAdapater(getActivity().getSupportFragmentManager(),list_title));
        mTlayoutNewsTitle.setupWithViewPager(mVpNewsShow);
        return view;
    }
}
