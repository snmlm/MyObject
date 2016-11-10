package com.ds.control;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private ToggleButton mTbMainOpenorclose;

    private void assignViews() {
        mTbMainOpenorclose = (ToggleButton) findViewById(R.id.tb_main_openorclose);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
        mTbMainOpenorclose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mTbMainOpenorclose.setBackgroundResource(R.mipmap.check_switch_on);
                    MyToast.newToast(MainActivity.this,"开").show();
                }else{
                    mTbMainOpenorclose.setBackgroundResource(R.mipmap.check_switch_off);
                    MyToast.newToast(MainActivity.this,"关").show();
                }
            }
        });
    }


    public static class MyToast{
        private static Toast mToast = null;
        private MyToast(){}

        public static Toast newToast(Context context,String str){
            synchronized (MyToast.class){
                if(mToast == null){
                    mToast = Toast.makeText(context,str,Toast.LENGTH_SHORT);
                }{
                    mToast.setText(str);
                }
            }
            return mToast;
        }
    }
}
