package com.jay.ActivityStack;

import android.app.Activity;
import android.util.Log;

import com.jay.PublicClass.PublicClass;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * activity监听器
 *
 * @author jay112233
 */

public class ActivityStack implements ActivityStackManager {

	public LinkedList<String> list = new LinkedList<String>();
	private int i;

	public ActivityStack() {

	}

	/** 需要新建表是调用 */
	public void ActivityStackAdd(int Flag) {
		PublicClass.list = this.list;
		this.list = new LinkedList<String>();
	}

	public void clear() {
		// TODO Auto-generated method stub
		list.clear();
	}

	public void push(Activity activity) {
		list.add(activity.getClass().getSimpleName());
		Log.i("", "this list" + list);
		Log.i("", "this size " + list.size());
	}

	public void pop() {
		// TODO Auto-generated method stub
		if (list.size() != 1) {
			Log.i("", "finish is " + list.getLast());
			list.removeLast();
			Log.i("", "this list " + list);
			Log.i("", "this size " + list.size());
		} else {
			list.clear();
		}
	}

	public String getTop() {
		// TODO Auto-generated method stub
		return list.getLast();
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return list.isEmpty();
	}

	public void clearTop() {
		list.removeLast();
	}

	public void allSee() {
		// TODO Auto-generated method stub
		ListIterator<String> iterator = list.listIterator();
		i = 1;
		while (iterator.hasNext()) {
			Log.i("",i + "." + iterator.next());
			i++;
		}
	}

	public String get(int i) {
		return list.get(i);
	}

	public LinkedList<String> ActivityManager() {
		return list;
	}

	public int size() {
		// TODO Auto-generated method stub
		return list.size();
	}

	public void IntentFlag(String name, String classname) {}
}
