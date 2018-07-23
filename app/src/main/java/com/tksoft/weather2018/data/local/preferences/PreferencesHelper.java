package com.tksoft.weather2018.data.local.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class PreferencesHelper {
    private SharedPreferences sharedPreferences;
    private Context mContext;

    public PreferencesHelper(Context context) {
        this.mContext = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public Context getmContext() {
        return mContext;
    }

    public void saveScreenWidth(int height) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PreferenceKeys.SCREEN_WIDTH, height);
        editor.commit();
    }

    public void saveScreenHeight(int height) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PreferenceKeys.SCREEN_HEIGHT, height);
        editor.commit();
    }

    public int getScreenWidth() {
        return sharedPreferences.getInt(PreferenceKeys.SCREEN_WIDTH, 0);
    }

    public int getScreenHeight() {
        return sharedPreferences.getInt(PreferenceKeys.SCREEN_HEIGHT, 0);
    }

    public boolean isFirstTimeUseApp() {
        return sharedPreferences.getBoolean(PreferenceKeys.FIRST_TIME_USE_APP, true);
    }

    public void saveStateFirstTimeUseApp(boolean state) throws NullPointerException {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PreferenceKeys.FIRST_TIME_USE_APP, state);
        editor.commit();
    }

    public void setBooleanSPR(String key, boolean isValue, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, isValue);
        editor.commit();
    }

    public boolean getBooleanSPR(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(key, false);
    }

    public void saveString(String key, String value, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getStringSPR(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, "");
    }



}
