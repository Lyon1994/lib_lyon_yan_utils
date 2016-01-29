package org.lyon_yan.app.android.lib_lyon_yan_utils.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.ViewCompat;

public class Background extends ViewCompat {
	private Paint backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private int mColor;
	private int mShadowColor;
	private float mShadowRadius;
	private float mShadowOffsetX;
	private float mShadowOffsetY;
	private static final float MIN_SHADOW_COLOR_ALPHA = 0.1f;

	public Background(Context context) {
		super();
		init();
		// TODO Auto-generated constructor stub
	}

	private void init() {
		// TODO Auto-generated method stub
		backgroundPaint.setColor(mColor);
		backgroundPaint.setStyle(Paint.Style.FILL);
		int shadowColor = changeColorAlpha(mShadowColor, MIN_SHADOW_COLOR_ALPHA);
		backgroundPaint.setShadowLayer(mShadowRadius, mShadowOffsetX,
				mShadowOffsetY, shadowColor);
	}

	private int changeColorAlpha(int color, float value) {
		int alpha = Math.round(Color.alpha(color) * value);
		int red = Color.red(color);
		int green = Color.green(color);
		int blue = Color.blue(color);
		return Color.argb(alpha, red, green, blue);
	}

}
