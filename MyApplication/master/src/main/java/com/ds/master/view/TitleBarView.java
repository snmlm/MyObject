package com.ds.master.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ds.master.R;


/**
 * Created by Administrator on 2016/9/30.
 */
public class TitleBarView extends LinearLayout{

    private final TextView mTvTopTitle;
    private final ImageButton mIbTopTitleLeft;

    public TitleBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = inflate(context, R.layout.layout_top, this);
        mTvTopTitle = (TextView) view.findViewById(R.id.tv_top_title);
        mIbTopTitleLeft = (ImageButton) findViewById(R.id.ib_top_title_left);
    }

    public void setTopTitleText(String name){
        mTvTopTitle.setText(String.format(getResources().getString(R.string.top_title),name));
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        mIbTopTitleLeft.setOnClickListener(l);
    }
}
