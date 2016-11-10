package com.ds.radiobutton;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/23.
 */
public class NewPagerAdaper extends PagerAdapter {

    private Context context;
    private ArrayList<Integer> images;

    public NewPagerAdaper(Context context,ArrayList<Integer> images){
        this.context = context;
        this.images = images;
    }
    /**
     * 获取item个个数
     * @return 返回个数
     */
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    /**
     * 是否创建一个新的页面
     * @param view 当前显示的页面（View）
     * @param object 下一页要显示的页面（View）
     * @return 返回true：表示不创建 false：表示创建
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;//如果进来的是同一个就不会创建新的了，否则创建新的
    }

    /**
     * 当item（页面）销毁的时候会调用
     * @param container ：viewpager
     * @param position ：当前页的位置
     * @param object ：当前要销毁的页面（View）
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);//执行会抛异常 不要执行
        //销毁的时候，将当前页在viewpager中移除
        container.removeView((View)object);
    }

    /**
     * 决定实例化每个页面的样式布局 item要展示的样子
     * @param container ：viewpager
     * @param position ：item位置
     * @return 返回item的视图（什么样子）
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(context, R.layout.viewpager_layout, null);
        ImageView image = (ImageView)view.findViewById(R.id.img_viewpager_img);
        image.setBackgroundResource(images.get(position%images.size()));
        container.addView(view);
        return view;
    }
}
