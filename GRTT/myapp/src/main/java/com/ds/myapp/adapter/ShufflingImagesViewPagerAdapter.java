package com.ds.myapp.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ds.myapp.R;
import com.ds.myapp.bean.ShuffliingInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/3.
 */

public class ShufflingImagesViewPagerAdapter extends PagerAdapter {
    private Context context;
    private List<ShuffliingInfo.ImagesBean> list = new ArrayList<>();


    public ShufflingImagesViewPagerAdapter(Context context){

        this.context = context;
    }


    public void setList(List<ShuffliingInfo.ImagesBean> list){
        this.list = list;
    }


    @Override
    public int getCount() {

        return list.size()*3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(context, R.layout.layout_shuffling_item, null);
        Glide.with(context).load(list.get(position%list.size()).getImgurl()).into((ImageView)view.findViewById(R.id.img_shuffling_item));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

}
