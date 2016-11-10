package com.ds.master.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.ds.master.R;
import com.ds.master.bean.FileInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/25.
 */

public class FileListViewAdapter extends BaseAdapter {

    private Context context;
    private List<FileInfo> list = new ArrayList<>();
    private Holder holder;

    public FileListViewAdapter(Context context){

        this.context = context;
    }

    public void setList(List<FileInfo> list){

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
            View view = View.inflate(context,R.layout.layout_file_item,null);
            holder = new Holder(view);
            view.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }
        FileInfo fileInfo = list.get(position);
        holder.mCbFileItem.setChecked(fileInfo.isClear);
        //holder.mImgFileItem.setImageDrawable(ne);
        holder.mTvFileItemName.setText(fileInfo.fileName);
        holder.mTvFileItemTime.setText(fileInfo.fileTime);
        holder.mTvFileItemSize.setText(fileInfo.fileSize);
        return holder.view;
    }

    private class Holder{
        public View view;
        public CheckBox mCbFileItem;
        public ImageView mImgFileItem;
        public TextView mTvFileItemName;
        public TextView mTvFileItemTime;
        public TextView mTvFileItemSize;

        public Holder(View v){
            this.view = v;
            mCbFileItem = (CheckBox) view.findViewById(R.id.cb_file_item);
            mImgFileItem = (ImageView) view.findViewById(R.id.img_file_item);
            mTvFileItemName = (TextView) view.findViewById(R.id.tv_file_item_name);
            mTvFileItemTime = (TextView) view.findViewById(R.id.tv_file_item_time);
            mTvFileItemSize = (TextView) view.findViewById(R.id.tv_file_item_size);
        }
    }
}
