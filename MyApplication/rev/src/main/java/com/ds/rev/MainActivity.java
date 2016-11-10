package com.ds.rev;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){
        sendBroadcast(new Intent("xxxx"));
        bindService(new Intent(this, ReviceR.class),new my(), BIND_AUTO_CREATE);
    }



    public class my implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            if(service!=null){
                Toast.makeText(MainActivity.this,((ReviceR.my)service).getnumber()+"", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
