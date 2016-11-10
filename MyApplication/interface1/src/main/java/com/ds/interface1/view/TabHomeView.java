package com.ds.interface1.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ds.interface1.R;

/**
 * Created by Administrator on 2016/9/30.
 */
public class TabHomeView extends LinearLayout {
    private TextView mTvTabHomeTop;
    private TextView mTvTabHomeBottom;
    private ImageView mImgTabHomeImg;

    public TabHomeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TabHomeView);


        View view = View.inflate(context, R.layout.layout_home_second_item,this);
        mTvTabHomeTop = (TextView) view.findViewById(R.id.tv_tab_home_top);
        mTvTabHomeBottom = (TextView) view.findViewById(R.id.tv_tab_home_bottom);
        mImgTabHomeImg = (ImageView) view.findViewById(R.id.img_tab_home_img);
        //设置上端文字
        mTvTabHomeTop.setText(typedArray.getString(R.styleable.TabHomeView_topTextView_text));
        //设置下端文字
        mTvTabHomeBottom.setText(typedArray.getString(R.styleable.TabHomeView_bottomTextView_text));
        //设置右侧图片
        mImgTabHomeImg.setBackgroundResource(typedArray.getResourceId(R.styleable.TabHomeView_rightImageView_background,R.mipmap.tab_home_activity1));
    }

    /**
     * 设置上端文字
     * @param str ： 文字
     */
    public void setTvTabHomeTopText(String str){
        mTvTabHomeTop.setText(str);
    }

    /**
     * 设置下端文字
     * @param str ：文字
     */
    public void setTvTabHomeBottomText(String str){
        mTvTabHomeBottom.setText(str);
    }

    /**
     * 设置右侧图片
     * @param id ： 资源id
     */
    public void setImgTabHomeImgBackground(int id){
        mImgTabHomeImg.setBackgroundResource(id);
    }
}
