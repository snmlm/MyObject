package com.ds.master.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ds.master.R;
import com.ds.master.bean.RunAppInfo;
import com.ds.master.utils.AppInfoUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/10/10.
 */
public class RocketListViewAdapter extends BaseAdapter {
    /** 上下文 */
    private Context context;
    private CheckBox mCbRocketAllselect;
    /** 运行应用集合 */
    private List<RunAppInfo> list;
    public static boolean isCheck;
    private boolean isSelect;

    private Handler handler;


    public RocketListViewAdapter(Context context, final CheckBox mCbRocketAllselect){
        this.context = context;

        /*for (RunAppInfo runAppInfo : list) {
            if(runAppInfo.isSystemApp){
                this.list.add(runAppInfo);
            }
        }*/
        this.mCbRocketAllselect = mCbRocketAllselect;

        handler = new Handler(context.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 0:
                        mCbRocketAllselect.setChecked(isSelect);
                        break;
                }
            }
        };

    }

    public List<RunAppInfo> getList(){
        return list;
    }

    public void setList(boolean isSystem){
        this.list = AppInfoUtils.newAppInfoUtils(context).getRunningAppList(isSystem);
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
        final Holder tag;
        if(view == null){
            view = View.inflate(context, R.layout.layout_rocket_lv_item,null);
            tag = new Holder(view);
            view.setTag(tag);
        }else{
            tag = (Holder) view.getTag();
        }
        //设置
        tag.mCbRocketItem.setTag(position);
        tag.mCbRocketItem.setChecked(list.get(position).isClear);
        tag.mTvRocketItemName.setText(list.get(position).appName);
        tag.mImgRocketItem.setImageDrawable(list.get(position).appIcon);
        tag.mTvRocketItemRam.setText(Formatter.formatFileSize(context,Integer.parseInt(list.get(position).appMemery)));
        tag.mTvRocketItemSystem.setVisibility(list.get(position).isSystemApp? View.VISIBLE:View.GONE);
        tag.mCbRocketItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                list.get((int)tag.mCbRocketItem.getTag()).isClear = isChecked;//设置可以删除
                if(!isChecked){
                    isCheck = true;
                    mCbRocketAllselect.setChecked(false);
                }
                new Thread(){
                    @Override
                    public void run() {
                        isSelect = true;
                        for (RunAppInfo runAppInfo : list) {
                            if(!runAppInfo.isClear)isSelect = false;
                        }
                        handler.sendEmptyMessage(0);
                    }
                }.start();
            }
        });
        return tag.view;
    }

    public class Holder{

        private View view;
        private CheckBox mCbRocketItem;
        private ImageView mImgRocketItem;
        private TextView mTvRocketItemName;
        private TextView mTvRocketItemRam;
        private TextView mTvRocketItemSystem;


        public Holder(View view){
            this.view = view;
            mCbRocketItem = (CheckBox) view.findViewById(R.id.cb_rocket_item);
            mImgRocketItem = (ImageView) view.findViewById(R.id.img_rocket_item);
            mTvRocketItemName = (TextView) view.findViewById(R.id.tv_rocket_item_name);
            mTvRocketItemRam = (TextView) view.findViewById(R.id.tv_rocket_item_ram);
            mTvRocketItemSystem = (TextView) view.findViewById(R.id.tv_rocket_item_system);
        }
    }
}
