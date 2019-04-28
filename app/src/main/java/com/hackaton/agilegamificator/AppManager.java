package com.hackaton.agilegamificator;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Dmitry Parshin on 27.04.2019.
 */
public class AppManager extends Application {

    private static final String SETTINGS_FILE_KEY = "SETTINGS_FILE_KEY";
    private static final String ACCOUNT_ID_KEY = "ACCOUNT_ID_KEY";
    private static final String DISPLAY_NAME_KEY = "DISPLAY_NAME_KEY";

    private static AppManager sInstance;

    private SharedPreferences mSharedPrefs;

    @NonNull
    public static AppManager getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mSharedPrefs = getSharedPreferences(SETTINGS_FILE_KEY, Context.MODE_PRIVATE);
    }

    public void saveAccountId(@NonNull String accountId) {
        mSharedPrefs.edit().putString(ACCOUNT_ID_KEY, accountId).apply();
    }

    @Nullable
    public String readAccountId() {
        return mSharedPrefs.getString(ACCOUNT_ID_KEY, null);
    }

    public void removeAccountId() {
        mSharedPrefs.edit().remove(ACCOUNT_ID_KEY).apply();
    }

    public void saveDisplayName(String displayName) {
        mSharedPrefs.edit().putString(DISPLAY_NAME_KEY, displayName).apply();
    }

    @Nullable
    public String readDisplayName() {
        return mSharedPrefs.getString(DISPLAY_NAME_KEY, null);
    }
}
