package com.ds.master.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ds.master.R;
import com.ds.master.bean.PhoneInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/25.
 */

public class TelListViewAdapter extends BaseAdapter {

    private Context context;
    private List<PhoneInfo> list = new ArrayList<>();
    private Hodler hodler;


    public TelListViewAdapter(Context context){

        this.context = context;
    }

    public void setList(List<PhoneInfo> list){
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
        PhoneInfo info = list.get(position);
        if(convertView == null){
            convertView = View.inflate(context, R.layout.layout_tel_item, null);
            hodler = new Hodler(convertView);
            convertView.setTag(hodler);
        }else{
            hodler = (Hodler) convertView.getTag();
        }
        hodler.mTvTelItemName.setText(info.name);
        hodler.mTvTelItemNamec.setText(info.namec);
        hodler.mTvTelItemNumber.setText(info.number);
        return hodler.view;
    }

    class Hodler{
        public View view;
        public TextView mTvTelItemName;
        public TextView mTvTelItemNamec;
        public TextView mTvTelItemNumber;

        public Hodler(View view){

            this.view = view;
            mTvTelItemName = (TextView) view.findViewById(R.id.tv_tel_item_name);
            mTvTelItemNamec = (TextView) view.findViewById(R.id.tv_tel_item_namec);
            mTvTelItemNumber = (TextView) view.findViewById(R.id.tv_tel_item_number);

        }
    }
}
