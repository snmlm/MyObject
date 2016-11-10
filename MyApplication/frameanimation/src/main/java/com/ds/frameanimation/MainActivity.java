package com.ds.frameanimation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {



    private ImageView mIvMainIv;
    private Button mBtnMainAlpha;
    private Button mBtnMainScale;
    private Button mBtnMainTranslate;
    private Button mBtnMainRotate;
    private Animation alpha;
    private Animation scale;
    private Animation translate;
    private Animation rotate;
    private AnimationSet aset;

    private void assignViews() {
        mIvMainIv = (ImageView) findViewById(R.id.iv_main_iv);
        mBtnMainAlpha = (Button) findViewById(R.id.btn_main_alpha);
        mBtnMainScale = (Button) findViewById(R.id.btn_main_scale);
        mBtnMainTranslate = (Button) findViewById(R.id.btn_main_translate);
        mBtnMainRotate = (Button) findViewById(R.id.btn_main_rotate);
        /*alpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
        scale = AnimationUtils.loadAnimation(this, R.anim.scale);
        translate = AnimationUtils.loadAnimation(this, R.anim.translate);
        rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        alpha.setFillAfter(true);
        scale.setFillAfter(true);
        translate.setFillAfter(true);
        rotate.setFillAfter(true);*/
        float density = this.getResources().getDisplayMetrics().density;
        alpha = new AlphaAnimation(1.0f,0.0f);
        alpha.setDuration(2000);
        alpha.setFillAfter(true);
        scale = new ScaleAnimation(1.0f,0.5f,1.0f,0.5f,50f*density,50f*density);
        scale.setDuration(2000);
        scale.setFillAfter(true);
        translate = new TranslateAnimation(0f,300f*density,0f,300f*density);
        translate.setDuration(2000);
        translate.setFillAfter(true);

        rotate = new RotateAnimation(0,360,50f*density,50f*density);
        rotate.setDuration(2000);
        rotate.setFillAfter(true);

        aset = new AnimationSet(true);
        aset.addAnimation(alpha);
        aset.addAnimation(scale);
        aset.addAnimation(translate);
        aset.addAnimation(rotate);



    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();

        AnimationDrawable anim = (AnimationDrawable) mIvMainIv.getBackground();
        anim.start();


    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_main_alpha:
                mBtnMainAlpha.startAnimation(aset);
                break;
            case R.id.btn_main_scale:
                mBtnMainScale.startAnimation(scale);
                break;
            case R.id.btn_main_translate:
                mBtnMainTranslate.startAnimation(translate);
                break;
            case R.id.btn_main_rotate:
                mBtnMainRotate.startAnimation(rotate);
                break;
        }
    }
}
