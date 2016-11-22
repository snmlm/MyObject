package com.ds.sanjihuancun;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout mActivityMain;
    private ImageView mImageImage;

    private void assignViews() {
        mActivityMain = (RelativeLayout) findViewById(R.id.activity_main);
        mImageImage = (ImageView) findViewById(R.id.image_image);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();


        new Thread(){
            @Override
            public void run() {
                final Bitmap image = ImageUtils.getIntance(MainActivity.this).getImage("http://192.168.1.124:8079/tomcat.png");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mImageImage.setImageBitmap(image);
                    }
                });
            }
        }.start();
    }
}
