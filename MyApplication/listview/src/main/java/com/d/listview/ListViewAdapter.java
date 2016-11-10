package com.d.listview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/21.
 */
public class ListViewAdapter extends BaseAdapter{


    private Context context;
    private ArrayList<String> titles;
    private Holder holder;
    private View view;

    public ListViewAdapter(Context context, ArrayList<String> titles){
        this.context = context;
        this.titles = titles;

    }

    @Override
    public int getCount() {
        return titles.size();
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
        view = convertView;//第一次为null
        if(view ==null){
            view = View.inflate(context,R.layout.item_main_listview_text,null);
            holder = new Holder(view);
            holder.getViewById();
            view.setTag(holder);
        }else{
            holder = (Holder) view.getTag();
        }
        holder.title.setText(titles.get(position));
        return view;
    }

    public void overlayArrayList(String title){
        this.titles.add(title);
    }


    public class Holder{

        public View view;
        public TextView title;

        public Holder(View view){
            this.view = view;
        }

        public void getViewById(){
             title =  (TextView) view.findViewById(R.id.tv_item_title);
        }
    }
}
