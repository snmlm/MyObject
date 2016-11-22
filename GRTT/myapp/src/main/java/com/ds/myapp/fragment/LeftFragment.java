package com.ds.myapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ds.myapp.R;
import com.ds.myapp.activity.HomeActivity;
import com.ds.myapp.fragment.menu.CommentFragment;
import com.ds.myapp.fragment.menu.FavorteFragment;
import com.ds.myapp.fragment.menu.LocalFragment;
import com.ds.myapp.fragment.menu.NewsFragment;
import com.ds.myapp.fragment.menu.PhotoFragment;
import com.ds.myapp.fragment.menu.SettingFragment;

/**
 * Created by Administrator on 2016/11/1.
 */

public class LeftFragment extends Fragment implements View.OnClickListener {
    private LinearLayout mLlayoutLeftMenu;
    private RelativeLayout mRlayoutLeftNewsShow;
    private RelativeLayout mRlayoutLeftNews;
    private RelativeLayout mRlayoutLeftFavoriteShow;
    private RelativeLayout mRlayoutLeftFavorite;
    private RelativeLayout mRlayoutLeftLocalShow;
    private RelativeLayout mRlayoutLeftLocal;
    private RelativeLayout mRlayoutHomeCommentShow;
    private RelativeLayout mRlayoutLeftComment;
    private RelativeLayout mRlayoutHomePhotoShow;
    private RelativeLayout mRlayoutLeftPhoto;
    private RelativeLayout mRlayoutHomeSettingShow;
    private RelativeLayout mRlayoutLeftSetting;
    private Fragment fragment;

    private void assignViews(View view) {
        mLlayoutLeftMenu = (LinearLayout) view.findViewById(R.id.llayout_left_menu);
        mRlayoutLeftNewsShow = (RelativeLayout) view.findViewById(R.id.rlayout_left_news_show);
        mRlayoutLeftNews = (RelativeLayout) view.findViewById(R.id.rlayout_left_news);
        mRlayoutLeftFavoriteShow = (RelativeLayout) view.findViewById(R.id.rlayout_left_favorite_show);
        mRlayoutLeftFavorite = (RelativeLayout) view.findViewById(R.id.rlayout_left_favorite);
        mRlayoutLeftLocalShow = (RelativeLayout) view.findViewById(R.id.rlayout_left_local_show);
        mRlayoutLeftLocal = (RelativeLayout) view.findViewById(R.id.rlayout_left_local);
        mRlayoutHomeCommentShow = (RelativeLayout) view.findViewById(R.id.rlayout_left_comment_show);
        mRlayoutLeftComment = (RelativeLayout) view.findViewById(R.id.rlayout_left_comment);
        mRlayoutHomePhotoShow = (RelativeLayout) view.findViewById(R.id.rlayout_left_photo_show);
        mRlayoutLeftPhoto = (RelativeLayout) view.findViewById(R.id.rlayout_left_photo);
        mRlayoutHomeSettingShow = (RelativeLayout) view.findViewById(R.id.rlayout_left_setting_show);
        mRlayoutLeftSetting = (RelativeLayout) view.findViewById(R.id.rlayout_left_setting);
    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_slidingmenu_left_fragment, container, false);
        assignViews(view);


        mRlayoutLeftNewsShow.setOnClickListener(this);
        mRlayoutLeftFavoriteShow.setOnClickListener(this);
        mRlayoutLeftLocalShow.setOnClickListener(this);
        mRlayoutHomeCommentShow.setOnClickListener(this);
        mRlayoutHomePhotoShow.setOnClickListener(this);
        mRlayoutHomeSettingShow.setOnClickListener(this);
        mRlayoutLeftNews.setBackgroundColor(0x66ffff00);

        return view;
    }

    @Override
    public void onClick(View v) {
        mRlayoutLeftNews.setBackgroundColor(0);
        mRlayoutLeftFavorite.setBackgroundColor(0);
        mRlayoutLeftLocal.setBackgroundColor(0);
        mRlayoutLeftComment.setBackgroundColor(0);
        mRlayoutLeftPhoto.setBackgroundColor(0);
        mRlayoutLeftSetting.setBackgroundColor(0);
        switch (v.getId()){
            case R.id.rlayout_left_news_show:
                mRlayoutLeftNews.setBackgroundColor(0x66ffff00);
                fragment = new NewsFragment();
                break;
            case R.id.rlayout_left_favorite_show:
                mRlayoutLeftFavorite.setBackgroundColor(0x66ffff00);
                fragment = new FavorteFragment();
                break;
            case R.id.rlayout_left_local_show:
                mRlayoutLeftLocal.setBackgroundColor(0x66ffff00);
                fragment = new LocalFragment();
                break;
            case R.id.rlayout_left_comment_show:
                mRlayoutLeftComment.setBackgroundColor(0x66ffff00);
                fragment = new CommentFragment();
                break;
            case R.id.rlayout_left_photo_show:
                mRlayoutLeftPhoto.setBackgroundColor(0x66ffff00);
                fragment = new PhotoFragment();
                break;
            case R.id.rlayout_left_setting_show:
                mRlayoutLeftSetting.setBackgroundColor(0x66ffff00);
                fragment = new SettingFragment();
                break;
        }
        ((HomeActivity) getActivity()).showFragment(fragment);
    }
}
