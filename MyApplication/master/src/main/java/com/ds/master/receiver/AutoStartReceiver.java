package com.ds.master.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ds.master.activity.GuideActivity;
import com.ds.master.utils.FileUtils;

/**
 * Created by Administrator on 2016/9/30.
 */
public class AutoStartReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(FileUtils.readInfo(context)[0]){
            Intent i = new Intent(context, GuideActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//创建一个新的栈来存放activity
            context.startActivity(i);
        }
    }
}
