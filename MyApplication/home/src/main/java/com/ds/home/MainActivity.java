package com.ds.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.ds.home.receiver.HomeWatcherReceiver;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onAttachedToWindow() {
        //this.getWindow().setType(WindowManager.LayoutParams.FIRST_SYSTEM_WINDOW+4);
        super.onAttachedToWindow();
    }
    private HomeWatcherReceiver homeWatcherReceiver;
    public static final int FLAG_HOMEKEY_DISPATCHED = 0x80000000; //需要自己定义标志

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getWindow().addFlags(FLAG_HOMEKEY_DISPATCHED);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*homeWatcherReceiver = new HomeWatcherReceiver();
        registerReceiver(homeWatcherReceiver,new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
        Toast.makeText(MainActivity.this, "已注册", Toast.LENGTH_SHORT).show();*/
    }

    @Override
    protected void onPause() {
        super.onPause();
        /*unregisterReceiver(homeWatcherReceiver);
        Toast.makeText(MainActivity.this, "已销毁", Toast.LENGTH_SHORT).show();*/
    }




    @Override
    public boolean onKeyDown( int keyCode, KeyEvent event) {
        Toast.makeText(MainActivity.this, "aaaaaa", Toast.LENGTH_SHORT).show();
        // TODO Auto-generated method stub
        if (keyCode == event. KEYCODE_HOME) {
            Toast.makeText(MainActivity.this, "aaaaaa", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
