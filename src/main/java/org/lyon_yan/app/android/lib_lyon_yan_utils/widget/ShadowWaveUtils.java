package org.lyon_yan.app.android.lib_lyon_yan_utils.widget;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;

import org.lyon_yan.app.android.lib_lyon_yan_utils.R;


@SuppressLint("InlinedApi")
public class ShadowWaveUtils {

    private static final long ANIMATION_DURATION = 200;
    private static final float MAX_SHADOW_COLOR_ALPHA = 0.4f;
    private static final float MIN_SHADOW_COLOR_ALPHA = 0.1f;
    private static final float SHADOW_OFFSET_X = 0.0f;
    private static final float SHADOW_OFFSET_Y = 4.0f;
    private static final float SHADOW_RADIUS = 8.0f;
    private static final int StateNormal = 1;
    private static final int StateTouchDown = 2;
    private static final int StateTouchUp = 3;

    public static ShadowWaveUtils BindView(@NonNull View view) {
        return new ShadowWaveUtils(view);
    }

    public static ShadowWaveUtils BindView(@NonNull View view,
                                           AttributeSet attrs) {
        return new ShadowWaveUtils(view, attrs);
    }

    public static ShadowWaveUtils BindView(@NonNull View view,
                                           AttributeSet attrs, int defStyleAttr) {
        return new ShadowWaveUtils(view, attrs, defStyleAttr);
    }

    private Paint backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF backgroundRectF;
    private int height;
    private int mColor;
    private int mCornerRadius;
    private Rect mFingerRect;
    private boolean mMoveOutside;
    private int mPadding;
    private int mShadowColor;
    private float mShadowOffsetX;
    private float mShadowOffsetY;
    private float mShadowRadius;
    private long mStartTime;
    private int mState = StateNormal;

    public void setCornerRadius(int mCornerRadius) {
        this.mCornerRadius = mCornerRadius;
    }

    public void setMoveOutside(boolean mMoveOutside) {
        this.mMoveOutside = mMoveOutside;
    }

    public void setPadding(int mPadding) {
        this.mPadding = mPadding;
    }

    public void setShadowOffsetX(float mShadowOffsetX) {
        this.mShadowOffsetX = mShadowOffsetX;
    }

    public void setShadowRadius(float mShadowRadius) {
        this.mShadowRadius = mShadowRadius;
    }

    public void setShadowOffsetY(float mShadowOffsetY) {
        this.mShadowOffsetY = mShadowOffsetY;
    }

    private CharSequence mText;
    private int mTextColor;
    private int mTextSize;

    private Point mTouchPoint = new Point();

    private Path rippleClipPath;

    private Paint ripplePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private TextPaint textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);

    private View view = null;

    private int width;

    private ShadowWaveUtils(@NonNull View view) {
        this(view, null);
    }

    private ShadowWaveUtils(@NonNull View view, AttributeSet attrs) {
        this(view, attrs, 0);
    }

    @SuppressWarnings({"deprecation"})
    private ShadowWaveUtils(@NonNull View view, AttributeSet attrs,
                            int defStyleAttr) {
        setView(view);
        if (getView() == null) {
            return;
        }
        try {
            mPadding = (int) getView().getResources().getDimension(
                    R.dimen.paper_padding);
        } catch (Exception e) {
            mPadding = 7;
        }
        TypedArray attributes = getView().getContext().obtainStyledAttributes(
                attrs, R.styleable.PaperButton);
        mColor = attributes.getColor(R.styleable.PaperButton_paper_color,
                getView().getResources().getColor(R.color.paper_button_color));
        mShadowColor = attributes.getColor(
                R.styleable.PaperButton_paper_shadow_color,
                getView().getResources().getColor(
                        R.color.paper_button_shadow_color));
        mCornerRadius = attributes.getDimensionPixelSize(
                R.styleable.PaperButton_paper_corner_radius,
                getView().getResources().getDimensionPixelSize(
                        R.dimen.paper_button_corner_radius));
        mText = attributes.getText(R.styleable.PaperButton_paper_text);
        mTextSize = attributes.getDimensionPixelSize(
                R.styleable.PaperButton_paper_text_size,
                getView().getResources().getDimensionPixelSize(
                        R.dimen.paper_text_size));
        mTextColor = attributes.getColor(
                R.styleable.PaperButton_paper_text_color, getView()
                        .getResources().getColor(R.color.paper_text_color));
        final String assetPath = attributes
                .getString(R.styleable.PaperButton_paper_font);
        if (assetPath != null) {
            try {
                AssetManager assets = getView().getContext().getAssets();
                Typeface typeface = Typeface.createFromAsset(assets, assetPath);
                textPaint.setTypeface(typeface);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mShadowRadius = attributes.getFloat(
                R.styleable.PaperButton_paper_shadow_radius, SHADOW_RADIUS);
        mShadowOffsetX = attributes.getFloat(
                R.styleable.PaperButton_paper_shadow_offset_x, SHADOW_OFFSET_X);
        mShadowOffsetY = attributes.getFloat(
                R.styleable.PaperButton_paper_shadow_offset_y, SHADOW_OFFSET_Y);
        attributes.recycle();

        backgroundPaint.setColor(mColor);
        backgroundPaint.setStyle(Paint.Style.FILL);
        int shadowColor = changeColorAlpha(mShadowColor, MIN_SHADOW_COLOR_ALPHA);
        if (Build.VERSION.SDK_INT > 14) {
            backgroundPaint.setShadowLayer(mShadowRadius, mShadowOffsetX,
                    mShadowOffsetY, shadowColor);
        }
        textPaint.setColor(mTextColor);
        textPaint.setTextSize(mTextSize);
        textPaint.setTextAlign(TextPaint.Align.CENTER);

        ripplePaint.setColor(darkenColor(mColor));

        getView().setWillNotDraw(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getView().setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    public void BindOnDraw(@NonNull Canvas canvas) {
        draw(canvas);
    }

    public boolean BindOnTouchEvent(@NonNull MotionEvent event) {
        return touchEvent(event);
    }

    public void BindWaveMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.setWidth(MeasureSpec.getSize(widthMeasureSpec));
        this.setHeight(MeasureSpec.getSize(heightMeasureSpec));
    }

    private int changeColorAlpha(int color, float value) {
        int alpha = Math.round(Color.alpha(color) * value);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }

    private int darkenColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.9f;
        return Color.HSVToColor(hsv);
    }

    public int getHeight() {
        if (getView() == null)
            return height;
        return getView().getHeight();
    }

    private RectF getRectF() {
        if (backgroundRectF == null) {
            backgroundRectF = new RectF();
            backgroundRectF.left = mPadding;
            backgroundRectF.top = mPadding;
            backgroundRectF.right = getWidth() - mPadding;
            backgroundRectF.bottom = getHeight() - mPadding;
        }
        return backgroundRectF;
    }

    public void setmPadding(int mPadding) {
        this.mPadding = mPadding;
    }

    public String getText() {
        return mText.toString();
    }

    public View getView() {
        return view;
    }

    public int getWidth() {
        if (getView() == null)
            return width;
        return getView().getWidth();
    }

    private void draw(@NonNull Canvas canvas) {
        if (getView() == null) {
            return;
        }
        int radius = 0;
        int shadowColor = changeColorAlpha(mShadowColor, MIN_SHADOW_COLOR_ALPHA);
        long elapsed = System.currentTimeMillis() - mStartTime;
        switch (mState) {
            case StateNormal:
                shadowColor = changeColorAlpha(mShadowColor, MIN_SHADOW_COLOR_ALPHA);
                break;
            case StateTouchDown:
                ripplePaint.setAlpha(255);
                if (elapsed < ANIMATION_DURATION) {
                    radius = Math.round(elapsed * getWidth() / 2
                            / ANIMATION_DURATION);
                    float shadowAlpha = (MAX_SHADOW_COLOR_ALPHA - MIN_SHADOW_COLOR_ALPHA)
                            * elapsed / ANIMATION_DURATION + MIN_SHADOW_COLOR_ALPHA;
                    shadowColor = changeColorAlpha(mShadowColor, shadowAlpha);
                } else {
                    radius = getWidth() / 2;
                    shadowColor = changeColorAlpha(mShadowColor,
                            MAX_SHADOW_COLOR_ALPHA);
                }
                getView().postInvalidate();
                break;
            case StateTouchUp:
                if (elapsed < ANIMATION_DURATION) {
                    int alpha = Math.round((ANIMATION_DURATION - elapsed) * 255
                            / ANIMATION_DURATION);
                    ripplePaint.setAlpha(alpha);
                    radius = getWidth()
                            / 2
                            + Math.round(elapsed * getWidth() / 2
                            / ANIMATION_DURATION);
                    float shadowAlpha = (MAX_SHADOW_COLOR_ALPHA - MIN_SHADOW_COLOR_ALPHA)
                            * (ANIMATION_DURATION - elapsed)
                            / ANIMATION_DURATION
                            + MIN_SHADOW_COLOR_ALPHA;
                    shadowColor = changeColorAlpha(mShadowColor, shadowAlpha);
                } else {
                    mState = StateNormal;
                    radius = 0;
                    ripplePaint.setAlpha(0);
                    shadowColor = changeColorAlpha(mShadowColor,
                            MIN_SHADOW_COLOR_ALPHA);
                }
                getView().postInvalidate();
                break;
        }
        if (Build.VERSION.SDK_INT > 14) {
            backgroundPaint.setShadowLayer(mShadowRadius, mShadowOffsetX,
                    mShadowOffsetY, shadowColor);
        }
        canvas.drawRoundRect(getRectF(), mCornerRadius, mCornerRadius,
                backgroundPaint);
        canvas.save();
        if (mState == StateTouchDown || mState == StateTouchUp) {
            if (rippleClipPath == null) {
                rippleClipPath = new Path();
                rippleClipPath.addRoundRect(getRectF(), mCornerRadius,
                        mCornerRadius, Path.Direction.CW);
            }
            canvas.clipPath(rippleClipPath);
        }
        canvas.drawCircle(mTouchPoint.x, mTouchPoint.y, radius, ripplePaint);
        canvas.restore();
        if (mText != null && mText.length() > 0) {
            int y = (int) (getHeight() / 2 - ((textPaint.descent() + textPaint
                    .ascent()) / 2));
            canvas.drawText(mText.toString(), getWidth() / 2, y, textPaint);
        }
    }

    private boolean touchEvent(@NonNull MotionEvent event) {
        if (getView() == null) {
            return false;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mMoveOutside = false;
                mFingerRect = new Rect(getView().getLeft(), getView().getTop(),
                        getView().getRight(), getView().getBottom());
                mTouchPoint.set(Math.round(event.getX()), Math.round(event.getY()));
                mState = StateTouchDown;
                mStartTime = System.currentTimeMillis();
                getView().invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                if (!mFingerRect.contains(getView().getLeft() + (int) event.getX(),
                        getView().getTop() + (int) event.getY())) {
                    mMoveOutside = true;
                    mState = StateNormal;
                    getView().invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (!mMoveOutside) {
                    mState = StateTouchUp;
                    mStartTime = System.currentTimeMillis();
                    getView().invalidate();
                    getView().performClick();
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                mState = StateNormal;
                getView().invalidate();
                break;
        }
        return true;
    }

    public void setColor(int color) {
        if (getView() == null)
            return;
        mColor = color;
        backgroundPaint.setColor(mColor);
        getView().invalidate();
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setShadowColor(int color) {
        if (getView() == null) {
            return;
        }
        mShadowColor = color;
        if (Build.VERSION.SDK_INT > 14) {
            backgroundPaint.setShadowLayer(mShadowRadius, mShadowOffsetX,
                    mShadowOffsetY, mShadowColor);
        }
        getView().invalidate();
    }

    public void setText(CharSequence text) {
        if (getView() == null) {
            return;
        }
        mText = text;
        getView().invalidate();
    }

    public void setTextColor(int color) {
        if (getView() == null) {
            return;
        }
        mTextColor = color;
        textPaint.setColor(mTextColor);
        getView().invalidate();
    }

    public void setTextSize(int pixel) {
        if (getView() == null) {
            return;
        }
        mTextSize = pixel;
        textPaint.setTextSize(mTextSize);
        getView().invalidate();
    }

    /**
     * @param color
     * @author Lyon_Yan <br/>
     * <b>time</b>: 2015年11月26日 下午5:13:14
     */
    public void setRippleColor(int color) {
        // TODO Auto-generated method stub
        ripplePaint.setColor(color);
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void BindText(String mText) {
        this.mText = mText;
    }

    public void BindTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
    }

}
