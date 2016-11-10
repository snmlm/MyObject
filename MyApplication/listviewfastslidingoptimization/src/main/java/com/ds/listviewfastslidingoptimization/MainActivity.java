package com.ds.listviewfastslidingoptimization;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AbsListView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListViewAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv = (ListView) findViewById(R.id.lv_main_lv);
        listViewAdapter = new ListViewAdapter(this);
        lv.setAdapter(listViewAdapter);
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState){
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING://快速滑动
                        listViewAdapter.isRuning = true;
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE://停止
                        listViewAdapter.isRuning = false;
                        listViewAdapter.notifyDataSetChanged();
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL://缓慢滑动
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }


}
