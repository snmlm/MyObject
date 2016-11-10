package com.jay.Test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.jay.ActivityStack.ActivityStackManager;
import com.jay.SuperActivity.SuperActivity;

public class testActivity extends SuperActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// Log.i(PublicClass.getActivity().toString(), "this activity is "
		// + PublicClass.getActivity() + "");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.testview);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		// ImageView i = (ImageView) findViewById(R.id.pic_main);
		// i.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// Intent intent = new Intent();
		// intent.setClass(testActivity.this, mainActivity.class);
		// // overridePendingTransition(enterAnim, exitAnim);
		// startActivity(intent);
		//
		// }
		// });
		Button button1 = (Button) findViewById(R.id.btn_test_see);
		button1.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				ActivityStackManager.activityStackManager.allSee();
			}
		});
		button1.setText("查看目前的Activity栈");
		Button button2 = (Button) findViewById(R.id.btn_test_goto);
		button2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(testActivity.this, mainActivity.class);
				startActivity(intent);
			}
		});
		button2.setText("前进");
		Button button3 = (Button) findViewById(R.id.btn_test_back);
		button3.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(testActivity.this, otherActivity.class);
				startActivity(intent);
			}
		});
		button3.setText("后退");

		Button button4 = (Button) findViewById(R.id.btn_test_finish);
		button4.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(testActivity.this, mainActivity.class);
				startActivity(intent);
				finish();
			}
		});
		button4.setText("前进并关闭Activity");
		// //t.setText(a.allSee().toString());
	}

}
