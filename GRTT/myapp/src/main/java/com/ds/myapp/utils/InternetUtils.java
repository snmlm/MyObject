package com.ds.myapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/11/7.
 */

public class InternetUtils {

    /**
     * 缓存文件大小 4M
     */
    private static final long CACHE_SIZE = 1024 * 1024 * 4;
    /**
     * 监听
     */
    private onCallBackListener listener;
    /**
     * 设置 通知
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    if (listener != null) {
                        if (msg.obj != null) {
                            listener.onSucceed((String) msg.obj);
                        } else {
                            listener.onFailure();
                        }
                    }
                    break;
            }
        }
    };


    /**
     * 获取网络是否可用
     *
     * @param context
     * @return
     */
    public boolean isNetAvailable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 利用okhttp3获取数据 并做缓存
     *
     * @param context
     * @param url
     * @param params
     */
    public InternetUtils getData(Context context, String url, HashMap<String, String> params) {
        //设置缓存
        OkHttpClient client = new OkHttpClient.Builder()
                .cache(new Cache(new File(context.getCacheDir(), MD5Utils.encode(url)), CACHE_SIZE))
                .build();
        //设置参数
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }

        //设置是否用缓存
        Request request = new Request.Builder()
                .cacheControl(isNetAvailable(context) ? CacheControl.FORCE_NETWORK : CacheControl.FORCE_CACHE)
                .url(url)
                .post(builder.build())
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = new Message();
                msg.obj = null;
                msg.what = 0;
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message msg = new Message();
                msg.obj = response.body().string();
                msg.what = 0;
                handler.sendMessage(msg);
            }
        });
        return this;
    }

    /**
     * 回调接口
     */
    public interface onCallBackListener {
        //访问成功
        void onSucceed(String json);

        //访问失败
        void onFailure();
    }

    /**
     * 回调设置
     *
     * @param listener
     */
    public InternetUtils callBack(onCallBackListener listener) {
        this.listener = listener;
        return this;
    }

}