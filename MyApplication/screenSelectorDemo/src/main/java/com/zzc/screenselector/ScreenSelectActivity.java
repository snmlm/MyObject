package com.zzc.screenselector;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ScreenSelectActivity extends Activity {

	private ViewPager vpActs;
	private ActsAdapter mAdapter;
	private ArrayList<View> vpViews = new ArrayList<View>();
	private final int vpViewsSize = 4;
	private LayoutInflater mInflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen_select_activity);
		mInflater = LayoutInflater.from(this);
		mAdapter = new ActsAdapter();
		vpActs = (ViewPager) findViewById(R.id.vp_acts);
		vpActs.setAdapter(mAdapter);
		vpActs.setCurrentItem(ScreenManager.getScreenManager().getActsSize());
	}

	class ActsAdapter extends PagerAdapter {
		@Override
		public int getCount() {
			return ScreenManager.getScreenManager().getActsSize();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
		}

		@Override
		public int getItemPosition(Object object) {

			return super.getItemPosition(object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, final int position) {
			View view = null;
			if (vpViews.size() <= position % vpViewsSize) {
				view = mInflater.inflate(R.layout.screen_select_item, null);
				vpViews.add(view);
			} else {
				view = vpViews.get(position % vpViewsSize);
				container.removeView(view);
			}
			Bitmap bmp = ScreenManager.getScreenManager().getBmp(position);
			if (bmp != null && !bmp.isRecycled()) {
				((ImageView) view.findViewById(R.id.iv_act))
						.setImageBitmap(bmp);
			}
			view.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					ScreenManager.getScreenManager().selectAct(position);
					ScreenSelectActivity.this.finish();
				}
			});
			container.addView(view);
			return view;
		}

	}

}
