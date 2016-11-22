package com.zx.mvp.model;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.zx.mvp.common.Common;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 类描述：Model(数据层)
 * 创建人：郭富东
 * 创建时间：2016/11/22 09:17
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class LoginModel implements LoginListenr{

    /** 登入结果监听接口 */
    private OnLoginListener mListener;

    /** 在主线程中处理登入结果 */
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(mListener != null){
               String json = (String) msg.obj;
                if(TextUtils.isEmpty(json)){//访问失败
                    mListener.failure();
                }else{//成功
                    mListener.succeed(json);
                }
            }
        }
    };

    @Override
    public void login(String name, String password, OnLoginListener listener) {
        mListener = listener;
        //创建拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//设置等级
        //创建客户端
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        //创建请求体
        final RequestBody body = new FormBody.Builder()
                .add("username",name)
                .add("password",password).build();
        //创建请求
        Request request = new Request.Builder().url(Common.BASE_URL + Common.REGISTER).post(body).build();
        //通过客户端发送请求
        client.newCall(request).enqueue(new Callback() {

            Message msg = handler.obtainMessage();
            @Override
            public void onFailure(Call call, IOException e) {
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //获取响应体
                ResponseBody body1 = response.body();
                msg.obj = body1.string();
                handler.sendMessage(msg);
            }
        });
    }
}
