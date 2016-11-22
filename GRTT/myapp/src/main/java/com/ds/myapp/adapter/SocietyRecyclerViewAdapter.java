package com.ds.myapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ds.myapp.R;
import com.ds.myapp.bean.AtlasBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/8.
 */

public class SocietyRecyclerViewAdapter extends RecyclerView.Adapter<SocietyRecyclerViewAdapter.Holder> {

    private Context context;
    private List<AtlasBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> list = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public SocietyRecyclerViewAdapter(Context context){

        this.context = context;
    }

    public void setList(List<AtlasBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public void addNewDatas(List<AtlasBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> mlist){
        list.addAll(0,mlist);
        notifyDataSetChanged();
    }
    public void addMoreDatas(List<AtlasBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> mlist){
        list.addAll(list.size(),mlist);
        notifyDataSetChanged();
    }

    public List<AtlasBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> getList(){
        return list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.layout_news_item_type_d, null);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        AtlasBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean info = list.get(position);
        Glide.with(context).load(info.img).into(holder.mImgNewsItemD);
        holder.mTvNewsItemD.setText(info.title);

        //判断是否设置了监听器
        if(mOnItemClickListener != null){
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView,position);
                }
            });
        }
        if(mOnItemLongClickListener != null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemLongClickListener.onItemLongClick(holder.itemView,position);
                    //返回true 表示消耗了事件 事件不会继续传递
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        public ImageView mImgNewsItemD;
        public TextView mTvNewsItemD;

        private void assignViews(View view) {
            mImgNewsItemD = (ImageView) view.findViewById(R.id.img_news_item_d);
            mTvNewsItemD = (TextView) view.findViewById(R.id.tv_news_item_d);
        }

        public Holder(View itemView) {
            super(itemView);
            assignViews(itemView);
        }
    }


    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }
}
