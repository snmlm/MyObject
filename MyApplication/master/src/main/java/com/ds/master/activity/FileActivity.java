package com.ds.master.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.ds.master.R;
import com.ds.master.adapter.FileListViewAdapter;
import com.ds.master.bean.FileInfo;
import com.ds.master.utils.FileIconAndType;
import com.ds.master.view.TitleBarView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/25.
 */

public class FileActivity extends BaseActivity{

    private Intent intent;
    private TitleBarView mTbnFileItemTitle;
    private TextView mTvFileCount;
    private TextView mTvFileSpace;
    private ListView mLvFileShow;
    private FileListViewAdapter fileListViewAdapter;


    private void assignViews() {
        mTbnFileItemTitle = (TitleBarView) findViewById(R.id.tbn_file_item_title);
        mTvFileCount = (TextView) findViewById(R.id.tv_file_count);
        mTvFileSpace = (TextView) findViewById(R.id.tv_file_space);
        mLvFileShow = (ListView) findViewById(R.id.lv_file_show);
    }



    @Override
    protected void initView() {
        setContentView(R.layout.activity_file);
        assignViews();
    }

    @Override
    protected void initData() {
        intent = getIntent();

        mTbnFileItemTitle.setTopTitleText(intent.getStringExtra("title"));
        getIntent().getBundleExtra("data");
        ArrayList<FileInfo> list = (ArrayList<FileInfo>) getIntent().getSerializableExtra("list");
        String size = getIntent().getStringExtra("size");
        if(list != null){
            mTvFileCount.setText(list.size()+" 个");
        }
        if (size != null){
            mTvFileSpace.setText(size);
        }



        switch (getIntent().getStringExtra("type")){
            case FileIconAndType.TYPE_ALL:

                break;
            case FileIconAndType.TYPE_TXT:

                break;
            case FileIconAndType.TYPE_VIDEO:

                break;
            case FileIconAndType.TYPE_AUDIO:

                break;
            case FileIconAndType.TYPE_IMAGE:

                break;
            case FileIconAndType.TYPE_ZIP:

                break;
            case FileIconAndType.TYPE_APK:

                break;
        }

        //获取数据

        fileListViewAdapter = new FileListViewAdapter(this);
        fileListViewAdapter.setList(list);
        fileListViewAdapter.notifyDataSetChanged();
        mLvFileShow.setAdapter(fileListViewAdapter);

    }

    @Override
    protected void setListener() {
        mTbnFileItemTitle.setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ib_top_title_left:
                    finish();
                    break;
            }
        }
    };
}
