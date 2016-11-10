package com.ds.getfrominternet.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.ds.getfrominternet.R;
import com.ds.getfrominternet.Utils.MD5Utils;
import com.ds.getfrominternet.adapter.NewsListViewAdapter;
import com.ds.getfrominternet.bean.NewsBean;
import com.ds.getfrominternet.view.RefreshListView;
import com.google.gson.Gson;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/10/28.
 */

public class NewsRActivity extends AppCompatActivity{
    private NewsBean newsBean;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    newsBean = new Gson().fromJson(((String) msg.obj).toString(), NewsBean.class);
                case 1:
                    newsListViewAdapter.setList(newsBean.getNews());
                    newsListViewAdapter.notifyDataSetChanged();
                    break;
                case 2:
                    NewsBean.News news = new NewsBean.News();
                    news.setIsClick(false);
                    news.setComment("1w");
                    news.setListimage("http://192.168.1.137:8079/img/a.jpg");
                    news.setPubdate("2016-2016-2016");
                    news.setTitle("今天的风儿很喧嚣");
                    newsBean.getNews().add(0,news);
                    newsListViewAdapter.setList(newsBean.getNews());
                    newsListViewAdapter.notifyDataSetChanged();
                    break;
                case 3:
                    NewsBean.News news1 = new NewsBean.News();
                    news1.setIsClick(false);
                    news1.setComment("1w");
                    news1.setListimage("http://192.168.1.137:8079/img/a.jpg");
                    news1.setPubdate("2016-2016-2016");
                    news1.setTitle("今天的风儿很喧嚣");
                    newsBean.getNews().add(newsBean.getNews().size(),news1);
                    newsListViewAdapter.setList(newsBean.getNews());
                    newsListViewAdapter.notifyDataSetChanged();
                    break;
            }


        }
    };
    private NewsListViewAdapter newsListViewAdapter;


    private RefreshListView mLvNewsShow;

    private void assignViews() {
        mLvNewsShow = (RefreshListView) findViewById(R.id.lv_news_show);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_refreshlistview);
        assignViews();


        mLvNewsShow.setOnRefreshListener(new RefreshListView.OnRefreshListener() {
            @Override
            public void OnPullRefresh() {
                handler.sendEmptyMessageAtTime(2,2000);
            }

            @Override
            public void OnLoadingMore() {
                handler.sendEmptyMessageAtTime(3,2000);
            }
        });
        //mLvNewsShow.addFooterView(View.inflate(this,R.layout.layout_listview_footer,null));

        newsListViewAdapter = new NewsListViewAdapter(NewsRActivity.this);
        mLvNewsShow.setAdapter(newsListViewAdapter);
        getFormInternet("http://192.168.1.137:8079/news.json");

        mLvNewsShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((TextView)view.findViewById(R.id.tv_news_item_name)).setTextColor(Color.rgb(0,255,0));//00ff00
                newsBean.getNews().get(position).setIsClick(true);
                handler.sendEmptyMessage(1);
            }
        });



    }


    private void getFormInternet(final String path){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(path).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {}

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                String json = response.body().string();
                //将数据做本地缓存
                saveDataToLoaal(path,json);
                Message msg = handler.obtainMessage();
                msg.what = 0;
                msg.obj = json;
                handler.sendMessage(msg);
            }
        });
    }

    private void saveDataToLoaal(String path,String json){
        File file = new File(getCacheDir(), MD5Utils.encode(path));
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(file));
            bos.write(json.getBytes());
            bos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(bos != null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
