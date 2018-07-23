package com.tksoft.weather2018.ui.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by New on 13/01/2017.
 */
public class TextViewWeather extends AppCompatTextView {

    public TextViewWeather(final Context type) {
        super(type);
        this.setType(type);
    }

    public TextViewWeather(final Context type, final AttributeSet set) {
        super(type, set);
        this.setType(type);
    }

    public TextViewWeather(final Context type, final AttributeSet set, final int n) {
        super(type, set, n);
        this.setType(type);
    }

    private void setType(final Context context) {
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf"));
    }

}
