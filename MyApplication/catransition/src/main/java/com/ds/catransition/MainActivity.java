package com.ds.catransition;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Timer(false).schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.toright_start_animation,R.anim.toright_end_animation);
            }
        },2000,4000);
    }
}
