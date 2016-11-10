package com.example.huichuan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/9/22.
 */
public class Second extends AppCompatActivity {

    private int num;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        if(getIntent()!=null){
            num = getIntent().getIntExtra("0x00",-1);
            ((TextView)findViewById(R.id.tv_second_tv)).setText("传过来的是："+num);
        }
    }

    public void onclick_second(View view){
        setResult(0x02,new Intent().putExtra("0x00",num+100));
        finish();
    }
}
