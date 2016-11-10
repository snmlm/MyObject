package com.ds.rev;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2016/10/8.
 */
public class Receiverreceiver extends BroadcastReceiver {
    private Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        //context.bindService(new Intent(context, ReviceR.class),new my(),Context.BIND_AUTO_CREATE);
        //context.startService(new Intent(context,ReviceR.class));
    }


}
