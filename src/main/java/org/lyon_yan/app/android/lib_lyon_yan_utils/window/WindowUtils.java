package org.lyon_yan.app.android.lib_lyon_yan_utils.window;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * Created by froger_mcs on 05.11.14.
 */
public class WindowUtils {
	private static int screenWidth = 0;
	private static int screenHeight = 0;

	public static int dpToPx(int dp) {
		return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	public static int getScreenHeight(Context c) {
		if (screenHeight == 0) {
			WindowManager wm = (WindowManager) c
					.getSystemService(Context.WINDOW_SERVICE);
			Display display = wm.getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);
			screenHeight = size.y;
		}

		return screenHeight;
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	public static int getScreenWidth(Context c) {
		if (screenWidth == 0) {
			WindowManager wm = (WindowManager) c
					.getSystemService(Context.WINDOW_SERVICE);
			Display display = wm.getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);
			screenWidth = size.x;
		}

		return screenWidth;
	}

	public static int getStatusHeight(Activity activity) {
		Rect frame = new Rect();
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		int statusBarHeight = frame.top;
		return statusBarHeight;
	}

	public static int getStatusHeight(Context context) {
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			return context.getResources().getDimensionPixelSize(x);
		} catch (Exception e1) {
			e1.printStackTrace();
			return 75;
		}
	}

	public static boolean isAndroid5() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
	}
}
