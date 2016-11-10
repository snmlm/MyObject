package com.zzc.screenselector;

import java.util.Stack;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.Display;
import android.view.View;

public class ScreenManager {
	private static Stack<Activity> actStack;
	private static ScreenManager instance;

	private ScreenManager() {
	}

	public static ScreenManager getScreenManager() {
		if (instance == null) {
			instance = new ScreenManager();
		}
		return instance;
	}

	public void popActivity() {
		Activity activity = actStack.lastElement();
		System.out.println("zzc----popActivity---->" + activity.toString());
		if (activity != null) {
			activity.finish();
			actStack.remove(activity);
			activity = null;
		}
	}

	public void popActivity(Activity activity) {
		if (activity != null) {
			activity.finish();
			actStack.remove(activity);
			activity = null;
		}
	}

	public Activity currentActivity() {
		Activity activity = actStack.lastElement();
		return activity;
	}

	public void pushActivity(Activity activity) {
		if (actStack == null) {
			actStack = new Stack<Activity>();
		}
		if (actStack.contains(activity))
			return;
		actStack.add(activity);
		System.out.println("zzc--pushActivity------>" + activity.toString());
	}

	public Bitmap getBmp(int pos) {
		if (pos >= actStack.size())
			return null;
		return getBmpByAct(actStack.elementAt(pos));
	}

	public Bitmap getBmpByAct(Activity act) {
		System.out.println("zzc---getBmpByAct----->" + act.toString());
		if (act == null)
			return null;
		View view = act.getWindow().getDecorView();
		view.buildDrawingCache();
		Rect rect = new Rect();
		view.getWindowVisibleDisplayFrame(rect);
		int statusBarHeights = rect.top;
		Display display = act.getWindowManager().getDefaultDisplay();
		view.setDrawingCacheEnabled(true);
		Bitmap bmp = Bitmap.createBitmap(view.getDrawingCache(), 0,
				statusBarHeights, display.getWidth(), display.getHeight()
						- statusBarHeights);
		view.destroyDrawingCache();
		return bmp;
	}

	public void popAllActivityExceptOne(Class cls) {
		while (true) {
			Activity activity = currentActivity();
			if (activity == null) {
				break;
			}
			if (activity.getClass().equals(cls)) {
				break;
			}
			popActivity(activity);
		}
	}

	public void selectAct(int pos) {
		if (actStack == null)
			return;
		if (pos < 0)
			return;
		while (actStack.size() > pos + 1) {
			popActivity();
		}
	}

	public int getActsSize() {
		if (actStack == null) {
			return 0;
		}
		return actStack.size();
	}
}