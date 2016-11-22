package com.ds.myapp.fragment.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ds.myapp.R;
import com.ds.myapp.activity.ShowNews;
import com.ds.myapp.adapter.RecommendListViewAdatper;
import com.ds.myapp.bean.NewsInfo;
import com.ds.myapp.utils.InternetUtils;
import com.ds.myapp.view.ShufflingImagesView;
import com.google.gson.Gson;

import java.util.HashMap;

import cn.bingoogolapple.refreshlayout.BGAMeiTuanRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 推荐
 * Created by xxxxx on 2016/11/1.
 */

public class RecommendFragment extends Fragment {


    private final static String PATH = "http://route.showapi.com/109-35?showapi_appid=26588" +
            "&showapi_sign=8d53afe7c04141c58f48e2ba79427c0e";
    private final static String TYPE_INIT = "TYPE_INIT";
    private final static String TYPE_REFRESH = "TYPE_REFRESH";
    private final static String TYPE_LOAD_MORE = "TYPE_LOAD_MORE";

    private BGARefreshLayout mBgarRecommendRefresh;
    private ListView mLvRecommendShow;
    private RecommendListViewAdatper recommendListViewAdatper;
    private int page = 1;


    private void assignViews(View view) {
        mBgarRecommendRefresh = (BGARefreshLayout) view.findViewById(R.id.bgar_recommend_refresh);
        mLvRecommendShow = (ListView) view.findViewById(R.id.lv_recommend_show);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_recommend_fragment, container, false);
        assignViews(view);

        BGAMeiTuanRefreshViewHolder meiTuanRefreshViewHolder = new BGAMeiTuanRefreshViewHolder(getActivity(), true);
        meiTuanRefreshViewHolder.setPullDownImageResource(R.mipmap.bga_refresh_mt_pull_down);
        meiTuanRefreshViewHolder.setChangeToReleaseRefreshAnimResId(R.drawable.bga_refresh_mt_change_to_release_refresh);
        meiTuanRefreshViewHolder.setRefreshingAnimResId(R.drawable.bga_refresh_mt_refreshing);

        mBgarRecommendRefresh.setRefreshViewHolder(meiTuanRefreshViewHolder);
        mBgarRecommendRefresh.setCustomHeaderView(new ShufflingImagesView().getView(getActivity()), true);

        recommendListViewAdatper = new RecommendListViewAdatper(getActivity());
        mLvRecommendShow.setAdapter(recommendListViewAdatper);

        //&channelId=5572a108b3cdc86cf39001ce&page=1&needAllList=0&maxResult=10
        initData(TYPE_REFRESH,1);
        setListener();
        return view;
    }



    public void setListener(){
        mBgarRecommendRefresh.setDelegate(new BGARefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
                initData(TYPE_REFRESH,1);
            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                initData(TYPE_LOAD_MORE,page++);
                return true;
            }
        });
        mLvRecommendShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                recommendListViewAdatper.setSelectMap(position);
                Intent intent = new Intent(getActivity(),ShowNews.class);
                intent.putExtra("url",((RecommendListViewAdatper.Holder) view.getTag()).url);
                startActivity(intent);
            }
        });

    }

    private void initData(final String dataType, int page){
        HashMap<String,String> prarm = new HashMap<>();
        prarm.put("channelId","5572a109b3cdc86cf39001df");
        prarm.put("needAllList","0");
        prarm.put("maxResult","20");
        prarm.put("page",String.valueOf(page));

        new InternetUtils().getData(getActivity(),PATH,prarm).callBack(new InternetUtils.onCallBackListener() {
            @Override
            public void onSucceed(String json) {
                mBgarRecommendRefresh.endRefreshing();
                NewsInfo newsInfo = new Gson().fromJson(json, NewsInfo.class);
                switch (dataType){
                    case TYPE_INIT:
                        recommendListViewAdatper.setList(newsInfo.showapi_res_body.pagebean.contentlist);
                        break;
                    case TYPE_REFRESH:
                        recommendListViewAdatper.addNewDatas(newsInfo.showapi_res_body.pagebean.contentlist);
                        recommendListViewAdatper.clearSelectMap();
                        mBgarRecommendRefresh.endRefreshing();
                        break;
                    case TYPE_LOAD_MORE:
                        recommendListViewAdatper.addMoreDatas(newsInfo.showapi_res_body.pagebean.contentlist);
                        mBgarRecommendRefresh.endLoadingMore();
                        break;
                }

            }

            @Override
            public void onFailure() {

            }
        });

    }
}
