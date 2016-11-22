package com.ds.myapp.activity;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.ds.myapp.R;
import com.ds.myapp.adapter.AtlasMinuteRecyclerViewAdapter;
import com.ds.myapp.bean.AtlasMinuteBean;
import com.ds.myapp.utils.InternetUtils;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * 显示新闻页
 * Created by xxxxx on 2016/11/7.
 */

public class AtlasActivity extends BaseActivity {

    private final static String PATH = "http://route.showapi.com/959-2?showapi_appid=26588" +
            "&showapi_sign=8d53afe7c04141c58f48e2ba79427c0e";
    private RecyclerView mRvAtlasPinterest;
    private AtlasMinuteRecyclerViewAdapter atlasMinuteRecyclerViewAdapter;

    private void assignViews() {
        mRvAtlasPinterest = (RecyclerView) findViewById(R.id.rv_atlas_pinterest);
    }

    @Override
    public void init() {
        setContentView(R.layout.activity_atlas);
        assignViews();
        String url = getIntent().getStringExtra("url");
        if(!url.isEmpty() && url != null){
            atlasMinuteRecyclerViewAdapter = new AtlasMinuteRecyclerViewAdapter(this);

            mRvAtlasPinterest.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
            mRvAtlasPinterest.setAdapter(atlasMinuteRecyclerViewAdapter);

            HashMap<String,String> prarm = new HashMap<>();
            prarm.put("id",url);

            new InternetUtils().getData(this,PATH,prarm).callBack(new InternetUtils.onCallBackListener() {
                @Override
                public void onSucceed(String json) {
                    atlasMinuteRecyclerViewAdapter.setList(new Gson().fromJson(json, AtlasMinuteBean.class).showapi_res_body.imgList);
                }

                @Override
                public void onFailure() {

                }
            });
        }
    }





}
