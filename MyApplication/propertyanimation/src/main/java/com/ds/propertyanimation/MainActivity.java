package com.ds.propertyanimation;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button mBtnMainBtn1;
    private Button mBtnMainBtn2;
    private Button mBtnMainBtn3;
    private Button mBtnMainBtn4;
    private Button mBtnMainBtn5;
    private ImageView mImgMainImg1;
    private ObjectAnimator objectAnimator;

    private void assignViews() {
        mBtnMainBtn1 = (Button) findViewById(R.id.btn_main_btn1);
        mBtnMainBtn2 = (Button) findViewById(R.id.btn_main_btn2);
        mBtnMainBtn3 = (Button) findViewById(R.id.btn_main_btn3);
        mBtnMainBtn4 = (Button) findViewById(R.id.btn_main_btn4);
        mBtnMainBtn5 = (Button) findViewById(R.id.btn_main_btn5);
        mImgMainImg1 = (ImageView) findViewById(R.id.img_main_img1);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_main_btn1:
                objectAnimator = ObjectAnimator.ofFloat(mImgMainImg1,"alpha",0.3f);
                objectAnimator.setDuration(2000).start();
                break;
            case R.id.btn_main_btn2:
                objectAnimator = ObjectAnimator.ofFloat(mImgMainImg1,View.SCALE_X,0.3f);
                objectAnimator.setDuration(2000).start();
                break;
            case R.id.btn_main_btn3:
                objectAnimator = ObjectAnimator.ofFloat(mImgMainImg1,View.ROTATION,360);
                objectAnimator.setDuration(2000).start();

                break;
            case R.id.btn_main_btn4:
                objectAnimator = ObjectAnimator.ofFloat(mImgMainImg1,View.TRANSLATION_X,200f);
                objectAnimator.setDuration(2000).start();
                break;
            case R.id.btn_main_btn5:
                objectAnimator.setCurrentPlayTime(0);
                break;
        }

    }
}
