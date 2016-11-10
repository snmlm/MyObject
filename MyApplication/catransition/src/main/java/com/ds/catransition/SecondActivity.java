package com.ds.catransition;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/9/26.
 */
public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText("第二个");
        setContentView(textView);
        new Timer(false).schedule(new TimerTask() {
            @Override
            public void run() {
                finish();
                overridePendingTransition(R.anim.toleft_start_animation, R.anim.toleft_end_animation);
            }
        },2000);

    }

}
