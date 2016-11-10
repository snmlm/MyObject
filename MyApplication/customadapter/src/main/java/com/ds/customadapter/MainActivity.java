package com.ds.customadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Spinner mSpMainDialog;

    private void assignViews() {
        mSpMainDialog = (Spinner) findViewById(R.id.sp_main_dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
        ArrayList<Integer> images = new ArrayList<Integer>();
        images.add(R.mipmap.ic_launcher);
        images.add(R.mipmap.ic_launcher);
        images.add(R.mipmap.ic_launcher);
        images.add(R.mipmap.ic_launcher);
        images.add(R.mipmap.ic_launcher);
        ArrayList<String> texts = new ArrayList<String>();
        texts.add("嘿嘿");
        texts.add("嘿嘿2");
        texts.add("嘿嘿3");
        texts.add("嘿嘿1");
        texts.add("嘿嘿0");

        mSpMainDialog.setAdapter(new SpinnerDialogAdapter(MainActivity.this,images,texts));
    }
}
