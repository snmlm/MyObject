package com.ds.myapp.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ds.myapp.R;
import com.ds.myapp.adapter.ShufflingImagesViewPagerAdapter;
import com.ds.myapp.bean.ShuffliingInfo;
import com.ds.myapp.utils.DensityUtils;
import com.ds.myapp.utils.MD5Utils;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/11/3.
 */

public class ShufflingImagesView{

    private final static String PATH = "http://192.168.1.119:8079/pics.json";

    private Context context;
    private View view;
    private ShufflingImagesViewPagerAdapter shufflingImagesViewPagerAdapter;
    private ShuffliingInfo shuffliingInfo;


    private ViewPager mVpShufflingShow;
    private TextView mRlayoutShufflingItemWord;
    private LinearLayout mRlayoutShufflingItemPointParent;
    private FileOutputStream fos;
    private FileInputStream fis;
    private static final int TIME = 4000;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int currentItem = mVpShufflingShow.getCurrentItem();
            mVpShufflingShow.setCurrentItem(currentItem+1);
            handler.postDelayed(runnable,TIME);
        }
    };


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    initData(msg.obj.toString());
                    handler.postDelayed(runnable,TIME);
                    break;
            }
        }
    };

    private void assignViews(View view) {
        mVpShufflingShow = (ViewPager) view.findViewById(R.id.vp_shuffling_show);
        mRlayoutShufflingItemWord = (TextView) view.findViewById(R.id.rlayout_shuffling_item_word);
        mRlayoutShufflingItemPointParent = (LinearLayout) view.findViewById(R.id.rlayout_shuffling_item_point_parent);
    }






    /**
     * 填加点
     */
    private void addPoint() {
        for (int i = 0; i < shuffliingInfo.getImages().size(); i++) {
            //创建视图
            View childView = new View(context);
            childView.setBackgroundResource(R.drawable.select_guide_point);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(DensityUtils.dp2px(context,10),
                    DensityUtils.dp2px(context,10));
            if(i != 0)layoutParams.leftMargin = DensityUtils.dp2px(context,10);//除了第一项  左侧margin
            if(i == 0)childView.setSelected(true); //第一个选中
            childView.setLayoutParams(layoutParams);
            final int finalI = i;
            childView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mVpShufflingShow.setCurrentItem(finalI + shuffliingInfo.getImages().size());
                }
            });
            //填加视图
            mRlayoutShufflingItemPointParent.addView(childView);
        }
    }

    public View getView(Context context){
        this.context = context;
        view = View.inflate(context, R.layout.layout_shuffling_image, null);

        assignViews(view);

        shufflingImagesViewPagerAdapter = new ShufflingImagesViewPagerAdapter(context);
        mVpShufflingShow.setAdapter(shufflingImagesViewPagerAdapter);

        setListener();

        //从本地获取
        String json = getDataFromLoacl();
        if(json != null){
            initData(json);
        }else{
            getDataFromInterent();
        }
        return view;
    }

    /**
     * 初始化数据
     * @param json
     */
    private void initData(String json) {
        shuffliingInfo = new Gson().fromJson(json, ShuffliingInfo.class);
        shufflingImagesViewPagerAdapter.setList(shuffliingInfo.getImages());
        shufflingImagesViewPagerAdapter.notifyDataSetChanged();
        mRlayoutShufflingItemWord.setText(shuffliingInfo.getImages().get(0).getTitle());
        mVpShufflingShow.setCurrentItem(shuffliingInfo.getImages().size(),false);
        addPoint();
    }


    /**
     * 设置监听
     */
    private void setListener() {
        mVpShufflingShow.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for(int i = 0 ; i <mRlayoutShufflingItemPointParent.getChildCount();i++){
                    mRlayoutShufflingItemPointParent.getChildAt(i).setSelected(position%shuffliingInfo.getImages().size() == i);
                }
                mRlayoutShufflingItemWord.setText(shuffliingInfo.getImages().get(position%shuffliingInfo.getImages().size()).getTitle());
                if(position <= shuffliingInfo.getImages().size()-1){
                    mVpShufflingShow.setCurrentItem(2*shuffliingInfo.getImages().size()-1,false);
                }
                if(position>=2*shuffliingInfo.getImages().size()){
                    mVpShufflingShow.setCurrentItem(shuffliingInfo.getImages().size(),false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mVpShufflingShow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        handler.removeCallbacks(runnable);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        handler.postDelayed(runnable,TIME);
                        break;
                }
                return false;
            }
        });



    }

    /**
     * 从网路获取数据
     */
    private void getDataFromInterent() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder requestBuilder = new Request.Builder().url(PATH);
        requestBuilder.method("GET",null);
        okHttpClient.newCall(requestBuilder.build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //请求失败
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //请求成功
                String json = response.body().string();
                saveDataToLoacl(json);
                Message msg = new Message();
                msg.what = 0;
                msg.obj =json;
                handler.sendMessage(msg);
            }
        });
    }

    private String getDataFromLoacl() {
        StringBuilder stringBuilder = new StringBuilder();

        File file = new File(context.getCacheDir(),MD5Utils.encode(PATH));
        if(!file.exists()){
            try {
                fis = new FileInputStream(file);
                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = fis.read(bytes,0,1024)) != -1){
                    stringBuilder.append(new String(bytes,0,len));
                }
                return stringBuilder.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if(fis!=null){
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }


    private void saveDataToLoacl(String json){
        try {
            fos = new FileOutputStream(new File(context.getCacheDir(),MD5Utils.encode(PATH)));
            fos.write(json.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}