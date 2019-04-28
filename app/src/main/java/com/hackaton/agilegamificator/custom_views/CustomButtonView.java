package com.hackaton.agilegamificator.custom_views;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.hackaton.agilegamificator.R;

import java.util.Locale;

/**
 * Created by Dmitry Parshin on 27.04.2019.
 */
public class CustomButtonView  extends AppCompatTextView {

    public static final int COLOR_BLUE = 1;
    public static final int COLOR_WHITE = 2;

    public CustomButtonView(Context context) {
        super(context);
        AssetManager am = getContext().getApplicationContext().getAssets();

        Typeface typeface = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Montserrat_Regular.ttf"));

        setTypeface(typeface);
        setAllCaps(true);
        setTextSize(16);
    }

    public CustomButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomButtonView, 0, 0);
        try {
            int color = ta.getInt(R.styleable.CustomButtonView_cb_color, COLOR_BLUE);
            if (color == COLOR_WHITE) {
                setTextColor(getResources().getColor(R.color.colorPrimary));
                setBackgroundResource(R.drawable.shape_btn_stroke);
            } else {
                setTextColor(getResources().getColor(R.color.white_ffffff));
                setBackgroundResource(R.drawable.blue_button);
            }
        } finally {
            ta.recycle();
        }

        setAllCaps(true);
        setTextSize(16);
    }

    public CustomButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAllCaps(true);
        setTextSize(16);
    }
}
