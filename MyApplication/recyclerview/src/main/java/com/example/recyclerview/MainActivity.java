package com.example.recyclerview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRvMainNews;

    private void assignViews() {
        mRvMainNews = (RecyclerView) findViewById(R.id.rv_main_news);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
        mRvMainNews.setLayoutManager( new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false));
        ArrayList<String> titles = new ArrayList<String>();
        titles.add("土耳其56岁男子收养多只金毛 出行浩浩荡荡有点帅");
        titles.add("看看你家房升值了吗？上周天津这个区房价涨幅超70%");
        titles.add("男子爬山发现地上一簇“虾饺”，经鉴定为”冥界之花”");
        titles.add("皇帝蟹——海鲜中的上品，蟹中之皇");
        titles.add("马蓉妈妈：王宝强未尽丈夫责任，宋喆经常照顾我马女儿");
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, titles);
        recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ((TextView) view.findViewById(R.id.tv_item)).setTextColor(Color.GRAY);
            }
        });
        mRvMainNews.setAdapter(recyclerViewAdapter);

    }
}
