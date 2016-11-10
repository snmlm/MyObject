package com.ds.customadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/20.
 */
public class SpinnerDialogAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<Integer> images;
    private ArrayList<String> texts;

    public SpinnerDialogAdapter(Context context,
                                ArrayList<Integer> images,
                                ArrayList<String> texts){
        this.context = context;
        this.images = images;
        this.texts = texts;
    }
    @Override
    public int getCount() {
        return images.size();
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

        View view = View.inflate(context, R.layout.item_main_spinner_dialog, null);
        ImageView image = (ImageView) view.findViewById(R.id.img_main_spinner_item);
        TextView text = (TextView) view.findViewById(R.id.tv_main_spinner_item);
        image.setBackgroundResource(images.get(position));
        text.setText(texts.get(position));
        return view;
    }

}
