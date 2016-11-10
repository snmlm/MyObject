package com.ds.master.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.ds.master.R;
import com.ds.master.bean.SingleNotification;
import com.ds.master.utils.FileUtils;

/**
 * Created by Administrator on 2016/9/29.
 */
public class SettingListViewAdapter extends BaseAdapter {


    private Context context;
    private int[] tags;
    private String[] names;
    private final boolean[] settingInfo;

    public SettingListViewAdapter(Context context, int[] tags, String[] names, boolean[] settingInfo){

        this.context = context;
        this.tags = tags;
        this.names = names;
        this.settingInfo = settingInfo;
    }


    @Override
    public int getCount() {
        return tags.length;
    }

    @Override
    public Object getItem(int position) {
        if(tags[position]==0){
            return  View.inflate(context, R.layout.layout_setting_item1,null);
        }else{
            return  View.inflate(context, R.layout.layout_setting_item2,null);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(tags[position] == 0){
            if(view == null){
                view = View.inflate(context, R.layout.layout_setting_item1,null);
                ToggleButton tb = (ToggleButton) view.findViewById(R.id.tb_setting_set);
                tb.setChecked(settingInfo[position]);
                    tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        settingInfo[position] = isChecked;
                        FileUtils.saveInnfo(context,settingInfo);
                        if(position == 1){
                            if(isChecked){
                                SingleNotification.newSingleNotification(context).openNotification();
                            }else{
                                SingleNotification.newSingleNotification(context).closeNotification();
                            }
                        }
                    }
                });
            }
        }else{
            if(view == null){
                view = View.inflate(context, R.layout.layout_setting_item2,null);
            }
        }
        ((TextView)view.findViewById(R.id.tv_setting_name)).setText(String.format(context.getResources().getString(R.string.setting_text_name),names[position]));
        return view;
    }
}
