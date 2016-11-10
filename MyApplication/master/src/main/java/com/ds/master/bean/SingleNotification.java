package com.ds.master.bean;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;

import com.ds.master.R;
import com.ds.master.activity.GuideActivity;

/**
 * Created by Administrator on 2016/10/9.
 */
public class SingleNotification {

    private static final int NOTIFCATION_ID = 1;
    private static SingleNotification singleNotification;
    private static Context context;

    private SingleNotification(){}

    public static SingleNotification newSingleNotification(Context context){
        SingleNotification.context = context;
        synchronized (SingleNotification.class){
            if(singleNotification == null){
                singleNotification = new SingleNotification();
            }
        }
        return singleNotification;
    }

    public void openNotification(){
        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!");
        Intent resultIntent = new Intent(context, GuideActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(GuideActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);//点击

        mBuilder.setOngoing(true);//删除

        ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE))
                .notify(NOTIFCATION_ID, mBuilder.build());
    }
    public void closeNotification(){
        ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE))
                .cancel(NOTIFCATION_ID);
    }

}
