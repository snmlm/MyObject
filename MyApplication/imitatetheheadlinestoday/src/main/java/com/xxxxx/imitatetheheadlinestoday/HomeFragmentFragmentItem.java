package com.xxxxx.imitatetheheadlinestoday;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by xxxxx on 2016/9/25.
 */
public class HomeFragmentFragmentItem extends Fragment {

    private TextView mTvHomeFragmentitem;
    private int pager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_fragment_item, null);
        mTvHomeFragmentitem = (TextView) view.findViewById(R.id.tv_home_fragmentitem);
        mTvHomeFragmentitem.setText(pager + "页");
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pager = getArguments().getInt("pager");
    }

    public static HomeFragmentFragmentItem newInstace(int position){
        HomeFragmentFragmentItem fragmentItem = new HomeFragmentFragmentItem();
        Bundle bundle = new Bundle();
        bundle.putInt("pager",position);
        fragmentItem.setArguments(bundle);
        return fragmentItem;
    }


}
