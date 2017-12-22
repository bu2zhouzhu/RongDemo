package com.example.bu2zh.rongdemo.sp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by yanlongluo on 21/12/2017.
 */

public class MessageSp {
    private static final String NAME = "message";
    private static final String ID = "id";

    private SharedPreferences mSp;

    public MessageSp(Context context) {
        mSp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public void setId(int id) {
        mSp.edit().putInt(ID, id).apply();
    }

    public int getId() {
        return mSp.getInt(ID, 0);
    }
}
