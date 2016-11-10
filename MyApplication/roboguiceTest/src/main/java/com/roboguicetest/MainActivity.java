package com.roboguicetest;

import com.google.inject.Inject;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectPreference;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends RoboActivity {
    @InjectView(R.id.txtv1)
    private TextView txtv1;
    @InjectView(R.id.btu1)
    private Button btu1;
    @InjectResource(R.string.hello_world) String hiStr;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtv1.setText(hiStr);       
        btu1.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				  Intent intentBatteryUsage = new Intent(Intent.ACTION_POWER_USAGE_SUMMARY);        
			        startActivity(intentBatteryUsage);
			}
		});
      
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
