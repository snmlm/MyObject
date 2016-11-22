package com.ds.myapp.utils;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.app.NotificationCompat;

import com.ds.myapp.R;

/**
 * Created by Administrator on 2016/11/10.
 */

public class SingleNotification {

    private static final int NOTIFCATION_ID = 1;
    private static SingleNotification singleNotification;
    private static Context context;

    private SingleNotification(){}

    public static SingleNotification getInstance(Context context){

        SingleNotification.context = context;
        synchronized (SingleNotification.class){
            if(singleNotification == null){
                singleNotification = new SingleNotification();
            }
        }
        return singleNotification;
    }

    public void showNotification(String text){
        ((NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE))
                .notify(NOTIFCATION_ID,
                        new NotificationCompat.Builder(context)
                                .setSmallIcon(R.mipmap.ic_launcher)//图标
                                .setContentTitle("My app")//标题
                                .setContentText(text)//内容
                                .setOngoing(false)//删除
                                /*.setContentIntent(TaskStackBuilder.create(context)
                                        .addNextIntent(new Intent())//意图
                                        .addParentStack(GuideActivity.class)//打开的页面
                                        .getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT))//点击*/
                                .build()//构建
                );
    }
}
