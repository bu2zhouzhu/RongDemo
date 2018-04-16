package com.example.bu2zh.rongdemo.sp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by yanlongluo on 29/12/2017.
 */

public class SettingSp {

    private SharedPreferences mSp;
    private static final String KEY_LANGUAGE = "language";

    public SettingSp(Context context) {
        mSp = context.getSharedPreferences("setting", Context.MODE_PRIVATE);
    }

    public void setLanguage(String locale) {
        mSp.edit().putString(KEY_LANGUAGE, locale).apply();
    }

    public String getLanguage() {
        return mSp.getString(KEY_LANGUAGE, null);
    }

}
