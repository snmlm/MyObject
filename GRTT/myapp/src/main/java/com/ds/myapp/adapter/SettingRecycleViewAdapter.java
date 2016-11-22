package com.ds.myapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ds.myapp.R;

import java.util.Map;

/**
 * Created by Administrator on 2016/11/11.
 */

public class SettingRecycleViewAdapter extends RecyclerView.Adapter<SettingRecycleViewAdapter.Hodler> {

    private Context context;
    private Map<String, Integer>[] list;
    private String[] names;
    private int[] ids;
    private onItemClickListener listener;

    public SettingRecycleViewAdapter(Context context,String[] names,int[] ids){

        this.context = context;
        this.names = names;
        this.ids = ids;

    }

    @Override
    public Hodler onCreateViewHolder(ViewGroup parent, int viewType) {
        Hodler hodler =  new Hodler(View.inflate(context, R.layout.layout_setting_item, null));
        return hodler;
    }

    @Override
    public void onBindViewHolder(Hodler holder, final int position) {
        holder.mRlayoutSettingShow.setBackgroundResource(ids[position]);
        holder.mTvSettingShow.setText(names[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    public interface onItemClickListener{
        void onClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener){

        this.listener = listener;
    }

    class Hodler extends RecyclerView.ViewHolder {

        public RelativeLayout mRlayoutSettingShow;
        public TextView mTvSettingShow;

        private void assignViews(View view) {
            mRlayoutSettingShow = (RelativeLayout) view.findViewById(R.id.rlayout_setting_show);
            mTvSettingShow = (TextView) view.findViewById(R.id.tv_setting_show);
        }


        public Hodler(View itemView) {
            super(itemView);
            assignViews(itemView);
        }
    }
}
