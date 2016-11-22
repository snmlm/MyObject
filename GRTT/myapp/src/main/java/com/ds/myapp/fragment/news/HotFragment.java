package com.ds.myapp.fragment.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ds.myapp.R;
import com.ds.myapp.activity.ShowNews;
import com.ds.myapp.adapter.HotRecyclerViewAdapter;
import com.ds.myapp.bean.BlackAndWhiteComicBean;
import com.ds.myapp.utils.InternetUtils;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * 推荐
 * Created by xxxxx on 2016/11/1.
 */

public class HotFragment extends Fragment {

    private final static String PATH = "http://route.showapi.com/958-1?showapi_appid=26588" +
            "&showapi_sign=8d53afe7c04141c58f48e2ba79427c0e";
    private RecyclerView mRlvHotShow;

    private HotRecyclerViewAdapter hotRecyclerViewAdapter;



    private void assignViews(View view) {
        mRlvHotShow = (RecyclerView) view.findViewById(R.id.rlv_hot_show);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hot_fragment, container, false);
        assignViews(view);
        mRlvHotShow.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        hotRecyclerViewAdapter = new HotRecyclerViewAdapter(getActivity());
        mRlvHotShow.setAdapter(hotRecyclerViewAdapter);
        initData(1);
        setListener();
        return view;
    }

     public void setListener(){
         hotRecyclerViewAdapter.setOnItemClickListener(new HotRecyclerViewAdapter.OnItemClickListener() {
             @Override
             public void onItemClick(View view, int position) {
                 Intent intent = new Intent(getActivity(),ShowNews.class);
                 intent.putExtra("url",hotRecyclerViewAdapter.getList().get(position).link);
                 startActivity(intent);
             }
         });
    }

    private void initData(int page){
        HashMap<String,String> prarm = new HashMap<>();
        prarm.put("page", String.valueOf(page));
        prarm.put("type","/category/mengchong");

        new InternetUtils().getData(getActivity(),PATH,prarm).callBack(new InternetUtils.onCallBackListener() {
            @Override
            public void onSucceed(String json) {
                BlackAndWhiteComicBean blackAndWhiteComicBean = new Gson().fromJson(json, BlackAndWhiteComicBean.class);
                hotRecyclerViewAdapter.setList(blackAndWhiteComicBean.showapi_res_body.pagebean.contentlist);
            }

            @Override
            public void onFailure() {
            }
        });

    }
}
