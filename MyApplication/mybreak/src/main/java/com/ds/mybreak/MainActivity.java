package com.ds.mybreak;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView mImg;
    private Button mBtn;
    private Toolbar mTb;
    private LinearLayout mLLayout;
    private List<Integer> imagesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mLLayout = (LinearLayout) findViewById(R.id.llayout);
        mImg = (ImageView)findViewById(R.id.img);
        mBtn = (Button) findViewById(R.id.btn);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "button show", Toast.LENGTH_SHORT).show();
            }
        });

        mTb = (Toolbar) findViewById(R.id.tb);
        setSupportActionBar(mTb);
        mTb.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item:

                        //效果重置



                        //设置图片
                        mImg.setImageResource(imagesList.get(new Random().nextInt(imagesList.size())));
                        //设置监听
                        setOnTouchListener();
                        //显示所有
                        mLLayout.setVisibility(View.VISIBLE);
                        mBtn.setVisibility(View.VISIBLE);
                        mImg.setVisibility(View.VISIBLE);
                        break;
                }
                return true;
            }
        });


        TypedArray typedArray = getResources().obtainTypedArray(R.array.images);
        imagesList = new ArrayList<>();
        for(int i = 0;i<typedArray.length();i++){
            imagesList.add(typedArray.getResourceId(i,0));
        }

        mImg.setImageResource(imagesList.get(new Random().nextInt(imagesList.size())));
    }

    private void setOnTouchListener() {
        mLLayout.setOnTouchListener(null);
        mBtn.setOnTouchListener(null);
        mImg.setOnTouchListener(null);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.title,menu);
        return true;
    }
}
