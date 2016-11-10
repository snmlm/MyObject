package com.ds.interface1.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ds.interface1.R;

/**
 * Created by Administrator on 2016/9/30.
 */
public class HomeListViewAdapter extends BaseAdapter {

    private Context context;
    private int[] images;

    public HomeListViewAdapter(Context context, int[] images){

        this.context = context;
        this.images = images;
    }
    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.layout_home_listview_item,null);
        ImageView mImgItemLeft = (ImageView) view.findViewById(R.id.img_item_left);
        TextView mTvItemTitle= (TextView) view.findViewById(R.id.tv_item_title);
        TextView  mTvItemCommentCount= (TextView) view.findViewById(R.id.tv_item_comment_count);
        TextView  mTvItemTime= (TextView) view.findViewById(R.id.tv_item_time);
        mImgItemLeft.setBackgroundResource(images[position]);
        return view;
    }
}
