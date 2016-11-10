package com.jay.PublicClass;

import android.app.Activity;

import java.util.LinkedList;


/** 公用类 存储数据的 */
public class PublicClass {
	/** Activity启动次数 */
	public static int StartActivity = 1;
	/** 实例化Activity */
	public static Activity thisactivity;

	/** 保存原list */
	public static LinkedList<String> list;

	/** 出错销毁list */
	public static boolean Exception = false;

	/** 获取实例 */
	public static Activity getActivity() {
		return thisactivity;
	}

	/** 得到实例 */
	public static void setActivity(Activity activity) {
		thisactivity = activity;
	}

	public static String toTag(Class<?> cls) {
		return cls.getClass().getSimpleName();
	}
}
