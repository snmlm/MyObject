package com.ds.listviewfastslidingoptimization;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/10/11.
 */
public class ListViewAdapter extends BaseAdapter {

    private Context context;
    public static boolean isRuning = false;

    public ListViewAdapter(Context context){

        this.context = context;
    }

    @Override
    public int getCount() {
        return 500;
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
        View view = View.inflate(context, R.layout.layout_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.img);
        if(isRuning){
            imageView.setBackgroundResource(R.mipmap.ic_launcher);
        }else{
            imageView.setBackgroundResource(R.mipmap.b_newcare_tabbar_press);
        }

        return view;
    }
}
