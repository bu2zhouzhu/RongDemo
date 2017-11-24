package com.example.bu2zh.rongdemo.sp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by luoyl on 11/18/17.
 */

public class ConfigSp {
    private static final String TOKEN = "token";
    private static final String ID = "id";

    private SharedPreferences mSp;

    public ConfigSp(Context context) {
        mSp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
    }

    public void setToken(String token) {
        mSp.edit().putString(TOKEN, token).apply();
    }

    public String getToken() {
        return mSp.getString(TOKEN, null);
    }

    public void setId(String id) {
        mSp.edit().putString(ID, id).apply();
    }

    public String getId() {
        return mSp.getString(ID, null);
    }

    public void clear() {
        mSp.edit()
                .putString(TOKEN, null)
                .putString(ID, null)
                .apply();
    }
}
