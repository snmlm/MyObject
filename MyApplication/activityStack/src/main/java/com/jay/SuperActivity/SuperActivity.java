package com.jay.SuperActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.jay.ActivityStack.ActivityStackManager;
import com.jay.PublicClass.PublicClass;

/**
 * 每一个Activity的父类，这样才能统计出来当前Activity栈情况
 *
 * @author jay112233
 *
 */


public abstract class SuperActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		PublicClass.setActivity(this);
		if (!PublicClass.Exception) {
			ActivityStackManager.activityStackManager.push(this);
		}
		Log.e("","onCreate~~");
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.e("","onRestart~~");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		Log.e("","onStop~~");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		Log.e("","onDestroy~~");

		super.onDestroy();
		// ActivityStackManager.activityStackManager.pop();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		Log.e("","onResume~~");

		super.onResume();

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		Log.e("","onStart~~");
		super.onStart();
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		Log.e("","finish~~");
		if (!PublicClass.Exception) {
			ActivityStackManager.activityStackManager.pop();
		}
		super.finish();
	}

}
