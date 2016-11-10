package com.ds.getfrominternet.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ds.getfrominternet.R;
import com.ds.getfrominternet.bean.NewsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/27.
 */

public class NewsListViewAdapter extends BaseAdapter {

    private Context context;
    private List<NewsBean.News> list = new ArrayList<>();
    private Hodler hodler;

    public NewsListViewAdapter(Context context){
        this.context = context;
    }

    public void setList(List<NewsBean.News> list){
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
        if(convertView == null){
            convertView = View.inflate(context, R.layout.layout_news_item,null);
            hodler = new Hodler(convertView);
            convertView.setTag(hodler);
        }else{
            hodler = (Hodler) convertView.getTag();
        }
        if(list.get(position).getIsClick()){
            hodler.mTvNewsItemName.setTextColor(Color.rgb(0,255,0));//FF4081
        }else{
            hodler.mTvNewsItemName.setTextColor(Color.rgb(255, 64, 129));//FF4081
        }
        Glide.with(context).load(list.get(position).getListimage()).into(hodler.mImgNewsItem);
        hodler.mTvNewsItemName.setText(list.get(position).getTitle());
        hodler.mTvNewsItemTime.setText(list.get(position).getPubdate());
        hodler.mTvNewsItemBrowser.setText(list.get(position).getComment());

        return hodler.view;
    }

    class Hodler{
        public View view;
        public ImageView mImgNewsItem;
        public TextView mTvNewsItemName;
        public TextView mTvNewsItemTime;
        public TextView mTvNewsItemBrowser;

        public Hodler(View view){

            this.view = view;
            mImgNewsItem = (ImageView) view.findViewById(R.id.img_news_item);
            mTvNewsItemName = (TextView) view.findViewById(R.id.tv_news_item_name);
            mTvNewsItemTime = (TextView) view.findViewById(R.id.tv_news_item_time);
            mTvNewsItemBrowser = (TextView) view.findViewById(R.id.tv_news_item_browser);
        }
    }
}
