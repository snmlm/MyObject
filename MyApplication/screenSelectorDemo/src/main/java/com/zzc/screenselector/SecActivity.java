package com.zzc.screenselector;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.TextView;

public class SecActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setContentView(R.layout.activity_main);
		View contentView = findViewById(R.id.rl_root);
		contentView.setBackgroundResource(getResources().getIdentifier(
				"p" + ScreenManager.getScreenManager().getActsSize() % 6,
				"drawable", "com.zzc.screenselector"));
		TextView tvBt = (TextView) findViewById(R.id.tv_bt);
		tvBt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				act.startActivity(new Intent(act, MainActivity.class));
			}
		});
		tvBt.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				act.startActivity(new Intent(act, ScreenSelectActivity.class));
				return false;
			}
		});
	}
}
