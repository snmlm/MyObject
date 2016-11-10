package com.jay.IntentClass;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.jay.ActivityStack.ActivityStackManager;
import com.jay.PublicClass.PublicClass;

/**
 * 监听Intent对Activity栈的影响 优先调用setClass方法（未做同步处理）
 *
 * @author jay112233
 * @version 0.1
 */
public class IntentClass extends Intent {

	private static String classThisname;
	private static String classTargetname;

	private int Last, Target;

	@Override
	public Intent setFlags(final int flags) {
		// TODO Auto-generated method stub
		switch (flags) {
			case Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT:
				break;
			// 清空栈顶
			case Intent.FLAG_ACTIVITY_CLEAR_TOP:
				ClearTop();
				break;
			case Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET:
				break;
			case Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS:
				break;
			case Intent.FLAG_ACTIVITY_FORWARD_RESULT:
				break;
			case Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY:
				break;
			case Intent.FLAG_ACTIVITY_MULTIPLE_TASK:
				break;
			// 新栈 ,不做修改，不重新建立监听
			case Intent.FLAG_ACTIVITY_NEW_TASK:
				break;
			case Intent.FLAG_ACTIVITY_NO_HISTORY:
				break;
			case Intent.FLAG_ACTIVITY_NO_USER_ACTION:
				break;
			case Intent.FLAG_ACTIVITY_NO_ANIMATION:
				break;
			case Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP:
				break;
			case Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED:
				break;
			case Intent.FLAG_ACTIVITY_REORDER_TO_FRONT:
				break;
			case Intent.FLAG_ACTIVITY_SINGLE_TOP:
				break;
			default:
				break;
		}
		return super.setFlags(flags);
	}

	@Override
	public Intent setClass(Context packageContext, Class<?> cls) {
		// TODO Auto-generated method stub
		classThisname = packageContext.getClass().getSimpleName();
		classTargetname = cls.getSimpleName();
		Log.i("", "classThisname" + classThisname + " classTargetname ="
				+ classTargetname);
		return super.setClass(packageContext, cls);
	}

	private void ClearTop() {
		boolean a = true;
		try {
			Log.i("", String.valueOf(ActivityStackManager.activityStackManager.size()));
			for (int i = 0; i < ActivityStackManager.activityStackManager
					.size(); i++) {
				if (classThisname
						.equals(ActivityStackManager.activityStackManager
								.get(i))) {
					Last = i;
					Log.i("", "Last " + Last);
				}
				if (classTargetname
						.equals(ActivityStackManager.activityStackManager
								.get(i))) {
					Target = i;
					Log.i("", "Target " + Last);

				}
				if (!classTargetname
						.equals(ActivityStackManager.activityStackManager
								.get(i))) {
					a = false;
				}
			}
			if (a) {
				for (int j = 0; j < Last - Target + 1; j++) {
					ActivityStackManager.activityStackManager.pop();
				}
			}
		} catch (NullPointerException e) {
			Log.e("", "~~~定义请按照先setFlag再setClass顺序");
			Log.e("", "~~~Activity监听栈无效，被销毁");
			PublicClass.Exception = true;
			ActivityStackManager.activityStackManager.clear();
			e.printStackTrace();
		}
	}
}
