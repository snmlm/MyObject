package com.ds.master.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ds.master.R;
import com.ds.master.bean.AppInfo;
import com.ds.master.utils.AppInfoUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/10/12.
 */
public class SoftListViewAdapter extends BaseAdapter {

    private Context context;
    private CheckBox checkBox;
    private List<AppInfo> list;
    private Holder holder;
    public static boolean isThis = false;
    private boolean isSelect = true;
    Handler handler;

    public SoftListViewAdapter(Context context,final CheckBox checkBox){

        this.context = context;
        this.checkBox = checkBox;

        handler = new Handler(context.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                checkBox.setChecked(isSelect);
            }
        };
    }

    public void setList(int tag){
        this.list = AppInfoUtils.newAppInfoUtils(context).getAppList(tag);
    }

    public List<AppInfo> getList(){
        return list;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = View.inflate(context, R.layout.layout_soft_lv_item, null);
            holder = new Holder(view);
            view.setTag(holder);
        }else{
            holder = (Holder)view.getTag();
        }
        //设置标记
        holder.mCbSoftItem.setTag(position);
        holder.mCbSoftItem.setChecked(list.get(position).isClear);
        holder.mImgSoftItem.setImageDrawable(list.get(position).appIcon);
        holder.mTvSoftItemName.setText(list.get(position).appName);
        holder.mTvSoftItemPackage.setText(list.get(position).appPackage);
        holder.mTvSoftItemVersion.setText(list.get(position).appVersionName);
        holder.mCbSoftItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                list.get((Integer) buttonView.getTag()).isClear = isChecked;
                if(!isChecked){
                    isThis = true;
                    checkBox.setChecked(false);
                }
                new Thread(){
                    @Override
                    public void run() {
                        isSelect = true;
                        for (AppInfo appInfo : list) {
                            if(!appInfo.isClear)isSelect = false;
                        }
                        handler.sendEmptyMessage(0);
                    }
                }.start();
            }
        });



        return view;
    }

    private class Holder{

        public View view;
        public CheckBox mCbSoftItem;
        public ImageView mImgSoftItem;
        public TextView mTvSoftItemName;
        public TextView mTvSoftItemPackage;
        public TextView mTvSoftItemVersion;

        public Holder(View view){
            this.view = view;
            mCbSoftItem = (CheckBox) view.findViewById(R.id.cb_soft_item);
            mImgSoftItem = (ImageView) view.findViewById(R.id.img_soft_item);
            mTvSoftItemName = (TextView) view.findViewById(R.id.tv_soft_item_name);
            mTvSoftItemPackage = (TextView) view.findViewById(R.id.tv_soft_item_package);
            mTvSoftItemVersion = (TextView) view.findViewById(R.id.tv_soft_item_version);
        }
    }
}
