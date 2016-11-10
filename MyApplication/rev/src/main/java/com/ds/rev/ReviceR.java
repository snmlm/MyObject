package com.ds.rev;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2016/10/8.
 */
public class ReviceR extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new my(){
            @Override
            public int getnumber() {
                return 11;
            }
        };
    }

    public class my extends Binder{
        public int getnumber(){
            return 10;
        }
    }
}
