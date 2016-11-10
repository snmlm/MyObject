package com.d.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private RefreshListView mLvMainNews;
    private ArrayList<String> titles;
    private ListViewAdapter listViewAdapter;

    private void assignViews() {
        mLvMainNews = (RefreshListView) findViewById(R.id.lv_main_news);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
        titles = new ArrayList<String>();
        titles.add("土耳其56岁男子收养多只金毛 出行浩浩荡荡有点帅");
        titles.add("看看你家房升值了吗？上周天津这个区房价涨幅超70%");
        titles.add("男子爬山发现地上一簇“虾饺”，经鉴定为”冥界之花”");
        titles.add("皇帝蟹——海鲜中的上品，蟹中之皇");
        titles.add("马蓉妈妈：王宝强未尽丈夫责任，宋喆经常照顾我马女儿");
        titles.add("土耳其56岁男子收养多只金毛 出行浩浩荡荡有点帅");
        titles.add("看看你家房升值了吗？上周天津这个区房价涨幅超70%");
        titles.add("男子爬山发现地上一簇“虾饺”，经鉴定为”冥界之花”");
        titles.add("皇帝蟹——海鲜中的上品，蟹中之皇");
        titles.add("马蓉妈妈：王宝强未尽丈夫责任，宋喆经常照顾我马女儿");
        listViewAdapter = new ListViewAdapter(MainActivity.this, titles);
        mLvMainNews.setAdapter(listViewAdapter);
        mLvMainNews.setOnRefreshListener(new RefreshListView.OnRefreshListener() {
            @Override
            public void OnPullRefresh() {
                titles.add(0,"adfadfadsfadsfa");
                listViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void OnLoadingMore() {
                titles.add("1");
                listViewAdapter.notifyDataSetChanged();
            }
        });




    }
}
