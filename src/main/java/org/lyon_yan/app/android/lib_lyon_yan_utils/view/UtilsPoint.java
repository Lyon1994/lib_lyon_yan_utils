package org.lyon_yan.app.android.lib_lyon_yan_utils.view;

import android.graphics.Point;
import android.view.View;

public class UtilsPoint {
	public static Point getLocationCenterPoint(View src, View target) {
		final int[] l0 = new int[2];
		src.getLocationOnScreen(l0);

		final int[] l1 = new int[2];
		target.getLocationOnScreen(l1);

		l1[0] = l1[0] - l0[0] + target.getWidth() / 2;
		l1[1] = l1[1] - l0[1] + target.getHeight() / 2;

		return new Point(l1[0], l1[1]);
	}
}
