package com.zzc.screenselector;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class BaseActivity extends Activity {
	protected View contentView;
	protected Activity act;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		act = this;
	}

	@Override
	protected void onPause() {
		ScreenManager.getScreenManager().pushActivity(this);
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		ScreenManager.getScreenManager().popActivity(this);
		super.onDestroy();
	}

	@Override
	public void setContentView(int layoutResID) {
		contentView = LayoutInflater.from(this).inflate(layoutResID, null);
		this.setContentView(contentView);
	}

	@Override
	public void setContentView(View view) {
		contentView = view;
		super.setContentView(view);
	}

	@Override
	public void setContentView(View view, LayoutParams params) {
		contentView = view;
		super.setContentView(view, params);
	}
}
