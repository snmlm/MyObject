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
import android.widget.Toast;

import com.ds.myapp.R;
import com.ds.myapp.activity.AtlasActivity;
import com.ds.myapp.adapter.SocietyRecyclerViewAdapter;
import com.ds.myapp.bean.AtlasBean;
import com.ds.myapp.utils.InternetUtils;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * 推荐
 * Created by xxxxx on 2016/11/1.
 */

public class SocietyFragment extends Fragment {

    private final static String PATH = "http://route.showapi.com/959-1?showapi_appid=26588" +
            "&showapi_sign=8d53afe7c04141c58f48e2ba79427c0e";

    private RecyclerView mRlvSocietyShow;

    private SocietyRecyclerViewAdapter societyRecyclerViewAdapter;

    private void assignViews(View view) {
        mRlvSocietyShow = (RecyclerView) view.findViewById(R.id.rlv_society_show);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_society_fragment, container, false);
        assignViews(view);
        mRlvSocietyShow.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        societyRecyclerViewAdapter = new SocietyRecyclerViewAdapter(getActivity());
        mRlvSocietyShow.setAdapter(societyRecyclerViewAdapter);
        initData(1);
        setListener();
        return view;
    }


    public void setListener(){
        societyRecyclerViewAdapter.setOnItemClickListener(new SocietyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(),AtlasActivity.class);
                intent.putExtra("url",societyRecyclerViewAdapter.getList().get(position).link);
                startActivity(intent);
            }
        });
    }

    private void initData(int page){
        HashMap<String,String> prarm = new HashMap<>();
        prarm.put("type","dmbz");

        new InternetUtils().getData(getActivity(),PATH,prarm).callBack(new InternetUtils.onCallBackListener() {
            @Override
            public void onSucceed(String json) {
                societyRecyclerViewAdapter.setList(new Gson().fromJson(json, AtlasBean.class).showapi_res_body.pagebean.contentlist);
            }

            @Override
            public void onFailure() {
                Toast.makeText(getActivity(), "失败", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
