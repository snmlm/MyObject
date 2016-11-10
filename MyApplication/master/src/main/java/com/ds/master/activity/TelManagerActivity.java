package com.ds.master.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ds.master.R;
import com.ds.master.adapter.TelListViewAdapter;
import com.ds.master.bean.PhoneInfo;
import com.ds.master.view.TitleBarView;

import java.util.ArrayList;
import java.util.List;


/**
 * 通讯大全
 * Created by xxxxx on 2016/9/28.
 */
public class TelManagerActivity extends BaseActivity {

    private static final String TAG = "TelManagerActivity";
    private TitleBarView mTbvTelTitle;
    private ListView mLvTelShow;
    private Intent intent;
    private List<PhoneInfo> list;
    private TelListViewAdapter telListViewAdapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            telListViewAdapter.setList(list);
            telListViewAdapter.notifyDataSetChanged();
        }
    };

    private void assignViews() {
        mTbvTelTitle = (TitleBarView) findViewById(R.id.tbv_tel_title);
        mLvTelShow = (ListView) findViewById(R.id.lv_tel_show);
    }


    @Override
    protected void initView() {
        setContentView(R.layout.activity_tel_manager);
        assignViews();
    }


    @Override
    protected void initData() {
        list = new ArrayList<>();
        intent = getIntent();
        mTbvTelTitle.setTopTitleText(intent.getStringExtra("title"));


        telListViewAdapter = new TelListViewAdapter(this);
        mLvTelShow.setAdapter(telListViewAdapter);
        new Thread() {
            @Override
            public void run() {
                //打开数据库
                SQLiteDatabase sqLiteDatabase = SQLiteDatabase.openDatabase("data/data/" + getPackageName() + "/assets/commonnum.db",
                        null, SQLiteDatabase.OPEN_READWRITE | SQLiteDatabase.CREATE_IF_NECESSARY);
                //创建游标
                Cursor data = sqLiteDatabase.rawQuery("select * from classlist", null);
                while (data.moveToNext()) {
                    String idx = data.getString(data.getColumnIndex("idx"));//table idx
                    String namec = data.getString(data.getColumnIndex("name"));//classlist name
                    Cursor data1 = sqLiteDatabase.rawQuery("select * from table" + idx, null);
                    while (data1.moveToNext()) {
                        String name = data1.getString(data1.getColumnIndex("name"));//table name
                        String number = data1.getString(data1.getColumnIndex("number"));//table number
                        list.add(new PhoneInfo(idx, name, namec, number));
                    }
                }
                handler.sendEmptyMessage(0);
                sqLiteDatabase.close();
            }
        }.start();
    }


    @Override
    protected void setListener() {
        mTbvTelTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TelManagerActivity.this, "手机管理", Toast.LENGTH_SHORT).show();
                finish();
                if (intent.getStringExtra("from").contains("home")) {
                    overridePendingTransition(R.anim.translate_t2b_enter,
                            R.anim.translate_t2b_exit);
                }

            }
        });

        mLvTelShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {//设置listview监听
                final PhoneInfo info = list.get(position);
                new AlertDialog.Builder(TelManagerActivity.this)
                        .setTitle("注意")
                        .setMessage("是否要拨打：" + info.name + " 的电话 \n \n TEL: " + info.number)
                        .setNegativeButton("取消", null)
                        .setPositiveButton("拨打", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (ActivityCompat.checkSelfPermission(TelManagerActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                    // TODO: Consider calling
                                    //    ActivityCompat#requestPermissions
                                    // here to request the missing permissions, and then overriding
                                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                    //                                          int[] grantResults)
                                    // to handle the case where the user grants the permission. See the documentation
                                    // for ActivityCompat#requestPermissions for more details.
                                    return;
                                }
                                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + info.number)));
                            }
                        }).show();
            }
        });
    }

    /**
     * 监听按键
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            finish();
            if(intent.getStringExtra("from").contains("home")){
                overridePendingTransition(R.anim.translate_t2b_enter,
                        R.anim.translate_t2b_exit);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
