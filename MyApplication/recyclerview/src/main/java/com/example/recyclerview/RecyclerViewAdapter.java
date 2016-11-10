package com.example.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/21.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerHolder> {

    private ArrayList<String> titles;
    private Context context;
    private OnItemClickListener listener;

    public RecyclerViewAdapter(Context context, ArrayList<String> titles){
        this.context = context;
        this.titles = titles;
    }

    /**
     * 创建ViewHolder
     * @param parent 父布局
     * @param viewType  类型
     * @return
     */
    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerHolder(View.inflate(context,R.layout.item_recyclerholder,null));
    }

    /**
     * 绑定Holder 根据item个数每次都会绑定
     * @param holder 每个item的hodler
     * @param position 角标
     */
    @Override
    public void onBindViewHolder(RecyclerHolder holder,final int position) {
        holder.titles.setText(titles.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v,position);
            }
        });
    }

    /**
     * 获取多少个item
     * @return
     */
    @Override
    public int getItemCount() {
        return titles.size();
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public static class RecyclerHolder extends RecyclerView.ViewHolder {

        public final ImageView images;
        public final TextView titles;


        public RecyclerHolder(View itemView) {
            super(itemView);
            images = (ImageView) itemView.findViewById(R.id.img_item);
            titles = (TextView) itemView.findViewById(R.id.tv_item);
        }
    }

}


