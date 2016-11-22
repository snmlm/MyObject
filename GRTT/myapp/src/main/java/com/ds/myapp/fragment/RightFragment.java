package com.ds.myapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ds.myapp.R;
import com.ds.myapp.activity.LoadActivity;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by Administrator on 2016/11/1.
 */

public class RightFragment extends Fragment implements View.OnClickListener {

    private LinearLayout mLlayoutRightMenu;
    private ImageView mImgRightLoad;
    private TextView mTvRightLoad;
    private Button mBtnRightShare;

    private void assignViews(View view) {
        mLlayoutRightMenu = (LinearLayout) view.findViewById(R.id.llayout_right_menu);
        mImgRightLoad = (ImageView) view.findViewById(R.id.img_right_load);
        mTvRightLoad = (TextView) view.findViewById(R.id.tv_right_load);
        mBtnRightShare = (Button) view.findViewById(R.id.btn_right_share);
    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_slidingmenu_right_fragment, container, false);
        assignViews(view);


        mBtnRightShare.setOnClickListener(this);
        mImgRightLoad.setOnClickListener(this);
        mTvRightLoad.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_right_share:
                showShare();
                break;
            case R.id.img_right_load:
                startActivity(new Intent(getContext(), LoadActivity.class));
                break;
            case R.id.tv_right_load:
                startActivity(new Intent(getContext(), LoadActivity.class));
                break;
        }
    }


    private void showShare() {
        ShareSDK.initSDK(getContext());
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(getContext());
    }
}
