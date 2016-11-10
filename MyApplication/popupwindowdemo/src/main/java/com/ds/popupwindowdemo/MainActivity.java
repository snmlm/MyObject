package com.ds.popupwindowdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity {

    private Button mBtnMainBtn;
    private PopupWindow popupWindow;

    private void assignViews() {
        mBtnMainBtn = (Button) findViewById(R.id.btn_main_btn);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
        mBtnMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow = new PopupWindow(View.inflate(MainActivity.this, R.layout.showpopupwindow,null),
                        WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        true);
                popupWindow.setAnimationStyle(R.style.popupwindowstyle);
                popupWindow.showAtLocation(mBtnMainBtn, Gravity.BOTTOM,0,0);


            }
        });
    }
}
