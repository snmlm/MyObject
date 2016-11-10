package com.jay.ActivityStack;

import android.app.Activity;

import java.util.LinkedList;

/**
 * activity 栈监听器
 *
 * @author jay112233
 */
public interface ActivityStackManager {

	/** 实例化activity栈 */
	ActivityStackManager activityStackManager = new ActivityStack();

	LinkedList<String> ActivityManager();

	/** 需要新建的时候再次调用,Flag可以为空 */
	void ActivityStackAdd(int Flag);

	/** 推送 */
	void push(Activity activity);

	/** 出栈 */
	void pop();

	/** 栈顶 */
	String getTop();

	/** 为空 */
	boolean isEmpty();

	/** 长度 */
	int size();

	/** 根据IntentFlag做修改 */
	void IntentFlag(String name, String classname);

	/** 位置的值 */
	String get(int i);

	/** 清空 */
	void clear();

	/** 查看 */
	void allSee();

}
