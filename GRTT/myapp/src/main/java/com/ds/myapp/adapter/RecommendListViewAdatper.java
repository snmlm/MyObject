package com.ds.myapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ds.myapp.R;
import com.ds.myapp.bean.NewsInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/11/3.
 */

public class RecommendListViewAdatper extends BaseAdapter {

    private final static int COUNT = 3;
    private final static int TYPE_A = 0;
    private final static int TYPE_B = 1;
    private final static int TYPE_C = 2;
    private Context context;
    private List<NewsInfo.ShowapiResBodyBean.PagebeanBean.ContentlistBean> list = new ArrayList<>();
    private Holder holder;
    /**
     * 选中集合
     */
    private HashMap<Integer,Boolean> selectMap = new HashMap<>();
    /**
     * 默认颜色
     */
    private static final int DEFAULTCOLOR = 0xff000000;
    /**
     * 选中颜色
     */
    private static final int SELECTEDCOLOR = 0xffff0000;

    public RecommendListViewAdatper(Context context) {
        this.context = context;
    }

    @Override
    public int getViewTypeCount() {
        return COUNT;
    }

    public void setList(List<NewsInfo.ShowapiResBodyBean.PagebeanBean.ContentlistBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void addNewDatas(List<NewsInfo.ShowapiResBodyBean.PagebeanBean.ContentlistBean> list){
        this.list.addAll(0,list);
        notifyDataSetChanged();
    }

    public void addMoreDatas(List<NewsInfo.ShowapiResBodyBean.PagebeanBean.ContentlistBean> list){
        this.list.addAll(this.list.size(),list);
        notifyDataSetChanged();
    }

    public void setSelectMap(int position){
        selectMap.put(position,true);
        notifyDataSetChanged();
    }

    public void clearSelectMap(){
        selectMap.clear();
    }

    @Override
    public int getItemViewType(int position) {
        switch (list.get(position).imageurls.size()) {
            case 0:
                return TYPE_C;
            case 1:
                return TYPE_B;
            default:
                return TYPE_A;
        }
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

        if (convertView == null) {
            switch (getItemViewType(position)) {
                case TYPE_A:
                    convertView = View.inflate(context, R.layout.layout_news_item_type_a, null);
                    holder = new HolderA(convertView);
                    break;
                case TYPE_B:
                    convertView = View.inflate(context, R.layout.layout_news_item_type_b, null);
                    holder = new HolderB(convertView);
                    break;
                case TYPE_C:
                    convertView = View.inflate(context, R.layout.layout_news_item_type_c, null);
                    holder = new HolderC(convertView);
                    break;
            }
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.setData(list.get(position),position);
        return convertView;
    }


    public static abstract class Holder {
        public View view;
        public String url;
        public abstract void setData(NewsInfo.ShowapiResBodyBean.PagebeanBean.ContentlistBean info,int position);
    }


    class HolderA extends Holder {
        public TextView mTvNewsItemATitle;
        public ImageView mImgNewsItemAA;
        public ImageView mImgNewsItemAB;
        public ImageView mImgNewsItemAC;

        private void assignViews(View view) {
            mTvNewsItemATitle = (TextView) view.findViewById(R.id.tv_news_item_a_title);
            mImgNewsItemAA = (ImageView) view.findViewById(R.id.img_news_item_a_a);
            mImgNewsItemAB = (ImageView) view.findViewById(R.id.img_news_item_a_b);
            mImgNewsItemAC = (ImageView) view.findViewById(R.id.img_news_item_a_c);
        }

        public HolderA(View view) {
            this.view = view;
            assignViews(view);
        }

        @Override
        public void setData(NewsInfo.ShowapiResBodyBean.PagebeanBean.ContentlistBean info,int position) {
            url = info.link;
            if(selectMap.containsKey(position)){
                mTvNewsItemATitle.setTextColor(SELECTEDCOLOR);
            }else{
                mTvNewsItemATitle.setTextColor(DEFAULTCOLOR);
            }
            mTvNewsItemATitle.setText(info.title);
            switch (info.imageurls.size()){
                case 2:
                    Glide.with(context).load(info.imageurls.get(0).url).placeholder(R.mipmap.big_loadpic_full_listpage).into(mImgNewsItemAA);
                    Glide.with(context).load(info.imageurls.get(1).url).placeholder(R.mipmap.big_loadpic_full_listpage).into(mImgNewsItemAB);
                    Glide.with(context).load(info.imageurls.get(1).url).placeholder(R.mipmap.big_loadpic_full_listpage).into(mImgNewsItemAC);
                    break;
                default:
                    Glide.with(context).load(info.imageurls.get(0).url).placeholder(R.mipmap.big_loadpic_full_listpage).into(mImgNewsItemAA);
                    Glide.with(context).load(info.imageurls.get(1).url).placeholder(R.mipmap.big_loadpic_full_listpage).into(mImgNewsItemAB);
                    Glide.with(context).load(info.imageurls.get(2).url).placeholder(R.mipmap.big_loadpic_full_listpage).into(mImgNewsItemAC);
                    break;
            }
        }
    }


    class HolderB extends Holder {
        public ImageView mImgNewsItemB;
        public TextView mTvNewsItemBTitle;
        public TextView mTvNewsItemBContent;

        private void assignViews(View view) {
            mImgNewsItemB = (ImageView) view.findViewById(R.id.img_news_item_b);
            mTvNewsItemBTitle = (TextView) view.findViewById(R.id.tv_news_item_b_title);
            mTvNewsItemBContent = (TextView) view.findViewById(R.id.tv_news_item_b_content);
        }

        public HolderB(View view) {
            this.view = view;
            assignViews(view);
        }

        @Override
        public void setData(NewsInfo.ShowapiResBodyBean.PagebeanBean.ContentlistBean info,int position) {
            url = info.link;
            if(selectMap.containsKey(position)){
                mTvNewsItemBTitle.setTextColor(SELECTEDCOLOR);
            }else{
                mTvNewsItemBTitle.setTextColor(DEFAULTCOLOR);
            }
            if (info.havePic) {
                Glide.with(context).load(info.imageurls.get(0).url).placeholder(R.mipmap.big_loadpic_full_listpage).into(mImgNewsItemB);
            }
            mTvNewsItemBTitle.setText(info.title);
            mTvNewsItemBContent.setText(info.desc);
        }
    }


    class HolderC extends Holder {
        public TextView mTvNewsItemCTitle;

        private void assignViews(View view) {
            mTvNewsItemCTitle = (TextView) view.findViewById(R.id.tv_news_item_c_title);
        }

        public HolderC(View view) {
            this.view = view;
            assignViews(view);
        }

        @Override
        public void setData(NewsInfo.ShowapiResBodyBean.PagebeanBean.ContentlistBean info,int position) {
            url = info.link;
            if(selectMap.containsKey(position)){
                mTvNewsItemCTitle.setTextColor(SELECTEDCOLOR);
            }else{
                mTvNewsItemCTitle.setTextColor(DEFAULTCOLOR);
            }
            mTvNewsItemCTitle.setText(info.title);
        }
    }
}
