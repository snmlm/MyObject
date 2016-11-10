package com.example.huichuan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void onclick_main(View view){
        Intent intent = new Intent(MainActivity.this, Second.class);
        intent.putExtra("0x00", number+100);
        startActivityForResult(intent,0x00);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0x00 && resultCode == 0x02 && data != null){
            number = data.getIntExtra("0x00",-1);
            ((TextView)findViewById(R.id.tv_main_tv)).setText("回传的是："+number);

        }
    }
}
