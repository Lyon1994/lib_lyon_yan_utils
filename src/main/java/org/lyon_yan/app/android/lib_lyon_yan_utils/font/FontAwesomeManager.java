package org.lyon_yan.app.android.lib_lyon_yan_utils.font;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * FontAwesome
 * 
 * @author Lyon_Yan <br/>
 *         <b>time</b>: 2015年9月26日 下午3:17:43
 */
public class FontAwesomeManager {
	/**
	 * https://github.com/FortAwesome/Font-Awesome/
	 */
	public static final String ROOT = "fonts/", FONTAWESOME = ROOT
			+ "fontawesome-webfont.ttf";
	public static Typeface getTypeface(Context context) {
		if (FONTAWESOME == null) {
			return null;
		}
		return Typeface.createFromAsset(context.getAssets(), FONTAWESOME);
	}

	public static Typeface getTypeface(Context context, String font) {
		return Typeface.createFromAsset(context.getAssets(), font);
	}

	public static void markAsIconContainer(View v, Typeface typeface) {
		if (v instanceof ViewGroup) {
			ViewGroup vg = (ViewGroup) v;
			for (int i = 0; i < vg.getChildCount(); i++) {
				View child = vg.getChildAt(i);
				markAsIconContainer(child, typeface);
			}
		} else if (v instanceof TextView) {
			((TextView) v).setTypeface(typeface);
		}
	}


}