package com.ds.xingzuo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    /**
     * 基本地址
     */
    private static final String PATH = "http://route.showapi.com/872-1?" +
            "showapi_appid=26588&" +
            "showapi_sign=8d53afe7c04141c58f48e2ba79427c0e";
    /**
     * 输入框
     */
    private EditText mEtMainInput;
    /**
     * 查询点击按钮
     */
    private Button mBtnMainQuery;
    /**
     * 显示的布局 用于显示隐藏
     */
    private LinearLayout mLlayoutMainShowParent;
    /**
     * 显示的布局 用于设置颜色
     */
    private LinearLayout mLlayoutMainShow;
    /**
     * 图片
     */
    private ImageView mImgMainShow;
    /**
     * 今日运势
     */
    private TextView mTvMainFortune;
    /**
     * 综合
     */
    private TextView mTvMainSynthesize;
    /**
     * 爱情
     */
    private TextView mTvMainLove;
    /**
     * 财运
     */
    private TextView mTvMainTreasure;
    /**
     * 工作
     */
    private TextView mTvMainWork;
    /**
     * 简评
     */
    private TextView mTvMainComment;
    /**
     * 数据
     */
    private DataBean dataBean;

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    dataBean = new Gson().fromJson((String) msg.obj, DataBean.class);
                    int[] ints = (int[]) DataUtils.getMap().get(dataBean.showapi_res_body.star);
                    String chineseName = (String) DataUtils.getMapChinese().get(dataBean.showapi_res_body.star);
                    mLlayoutMainShowParent.setVisibility(View.VISIBLE);
                    mLlayoutMainShow.setBackgroundColor(ints[1]);
                    mImgMainShow.setBackgroundResource(ints[0]);
                    mTvMainFortune.setText(chineseName+"座今日运势");
                    mTvMainSynthesize.setText(dataBean.showapi_res_body.day.summary_star + "星");
                    mTvMainLove.setText(dataBean.showapi_res_body.day.love_star + "星");
                    mTvMainTreasure.setText(dataBean.showapi_res_body.day.money_star + "星");
                    mTvMainWork.setText(dataBean.showapi_res_body.day.work_star + "星");
                    mTvMainComment.setText("        "+dataBean.showapi_res_body.day.general_txt);
                    mTvMainComment.setTextColor(ints[1]);
                    break;
            }

        }
    };


    private void assignViews() {
        mEtMainInput = (EditText) findViewById(R.id.et_main_input);
        mBtnMainQuery = (Button) findViewById(R.id.btn_main_query);
        mLlayoutMainShowParent = (LinearLayout) findViewById(R.id.llayout_main_show_parent);
        mLlayoutMainShow = (LinearLayout) findViewById(R.id.llayout_main_show);
        mImgMainShow = (ImageView) findViewById(R.id.img_main_show);
        mTvMainFortune = (TextView) findViewById(R.id.tv_main_fortune);
        mTvMainSynthesize = (TextView) findViewById(R.id.tv_main_synthesize);
        mTvMainLove = (TextView) findViewById(R.id.tv_main_love);
        mTvMainTreasure = (TextView) findViewById(R.id.tv_main_treasure);
        mTvMainWork = (TextView) findViewById(R.id.tv_main_work);
        mTvMainComment = (TextView) findViewById(R.id.tv_main_comment);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
        mLlayoutMainShowParent.setVisibility(View.GONE);
        mBtnMainQuery.setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String star = mEtMainInput.getText().toString().trim();
            if (star.isEmpty()){
                Toast.makeText(MainActivity.this, "输入内容为空", Toast.LENGTH_SHORT).show();
            }else{
                if(DataUtils.getMap().containsKey(star)){
                    getData(star);
                }else{
                    Toast.makeText(MainActivity.this, "输入内容无效", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    /**
     * 获取数据
     * @param star
     */
    private void getData(String star) {
        Log.d(TAG, "getData: run");
        //判断网络是否可用
        if(InternetUtils.isNetworkAvailable(this)){
            //可用
            getDataFormInternet(star);
        }else{
            //不可用
            getDataFormLoacl(star);
        }

    }

    private void getDataFormLoacl(String star) {
        Log.d(TAG, "getDataFormLoacl: run");
        Toast.makeText(this, "当前网络不可用", Toast.LENGTH_SHORT).show();
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(getCacheDir(),MD5Utils.encode(star));
        if(file.exists()){
            try {
                FileInputStream fis = new FileInputStream(file);
                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = fis.read(bytes,0,1024))!= -1){
                    stringBuilder.append(new String(bytes,0,len));
                }
                Message msg= new Message();
                msg.what = 0;
                msg.obj = stringBuilder.toString();
                handler.sendMessage(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void getDataFormInternet(String star) {
        Log.d(TAG, "getDataFormInternet: run");
        Cache cache = new Cache(new File(getCacheDir(),MD5Utils.encode(star)),1024*1024*10);
        OkHttpClient client = new OkHttpClient().newBuilder().cache(cache).build();
        RequestBody body = new FormBody.Builder().add("star",star).build();
        Request builder = new Request.Builder().url(PATH).post(body).build();
        client.newCall(builder).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message msg = new Message();
                msg.obj = response.body().string();
                msg.what = 0;
                handler.sendMessage(msg);
            }
        });

    }
}
