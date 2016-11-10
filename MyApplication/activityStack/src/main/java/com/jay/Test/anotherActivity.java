package com.jay.Test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.jay.ActivityStack.ActivityStackManager;
import com.jay.IntentClass.IntentClass;
import com.jay.SuperActivity.SuperActivity;

public class anotherActivity extends SuperActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		setContentView(R.layout.anotherview);
		init();
	}

	private void init() {
		Button button1 = (Button) findViewById(R.id.btn_another_see);
		button1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ActivityStackManager.activityStackManager.allSee();
			}
		});
		button1.setText("查看目前的Activity栈");

		Button button2 = (Button) findViewById(R.id.btn_another_goto);
		button2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(anotherActivity.this, testActivity.class);
				startActivity(intent);
			}
		});
		button2.setText("前进");
		Button button3 = (Button) findViewById(R.id.btn_another_back);
		button3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				IntentClass intent = new IntentClass();
				intent.setClass(anotherActivity.this, mainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				startActivity(intent);
				finish();
			}
		});
		button3.setText("后退到testActivity");

		Button button4 = (Button) findViewById(R.id.btn_another_finish);
		button4.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(anotherActivity.this, testActivity.class);
				startActivity(intent);
				finish();
			}
		});
		button4.setText("前进并关闭Activity");
	}
}
