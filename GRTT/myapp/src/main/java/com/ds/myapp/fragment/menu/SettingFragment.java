package com.ds.myapp.fragment.menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ds.myapp.R;
import com.ds.myapp.activity.BaseActivity;
import com.ds.myapp.adapter.SettingRecycleViewAdapter;

/**
 * Created by Administrator on 2016/11/11.
 */

public class SettingFragment extends Fragment {

    private RecyclerView mRvSettingShow;
    private static final String[] NAMES = {"Red","Pink","Purple","DeepPurple","Indigo","Blue",
            "LightBlue","Cyan","Teal","Green","LightGreen","Lime","Yellow","Amber","Orange",
            "DeepOrange","Brown","Grey","BlueGrey"};
    private static final int[] IDS = {R.color.button_normal_red,R.color.button_normal_pink,
            R.color.button_normal_purple,R.color.button_normal_deep_purple,R.color.button_normal_indigo,
            R.color.button_normal_blue,R.color.button_normal_light_blue,R.color.button_normal_cyan,
            R.color.button_normal_teal,R.color.button_normal_green,R.color.button_normal_light_green,
            R.color.button_normal_lime,R.color.button_normal_yellow,R.color.button_normal_amber,
            R.color.button_normal_orange,R.color.button_normal_deep_orange,R.color.button_normal_brown,
            R.color.button_normal_grey,R.color.button_normal_blue_grey};
    private static final int[] THEMES = {R.style.RedTheme,R.style.PinkTheme,R.style.PurpleTheme,
            R.style.DeepPurpleTheme,R.style.IndigoTheme,R.style.BlueTheme,R.style.LightBlueTheme,
            R.style.CyanTheme, R.style.TealTheme,R.style.GreenTheme,R.style.LightGreenTheme,
            R.style.LimeTheme, R.style.YellowTheme, R.style.AmberTheme,R.style.OrangeTheme,
            R.style.DeepOrangeTheme,R.style.BrownTheme,R.style.GreyTheme,R.style.BlueGreyTheme};

    private void assignViews(View view) {
        mRvSettingShow = (RecyclerView) view.findViewById(R.id.rv_setting_show);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_setting_fragment, container, false);
        assignViews(view);
        mRvSettingShow.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayout.VERTICAL,false));
        SettingRecycleViewAdapter settingRecycleViewAdapter = new SettingRecycleViewAdapter(getActivity(), NAMES, IDS);
        mRvSettingShow.setAdapter(settingRecycleViewAdapter);
        settingRecycleViewAdapter.setOnItemClickListener(new SettingRecycleViewAdapter.onItemClickListener() {
            @Override
            public void onClick(int position) {
                BaseActivity activity = (BaseActivity) getActivity();
                activity.theme = THEMES[position];
                activity.recreate();
            }
        });
        return view;
    }
}
