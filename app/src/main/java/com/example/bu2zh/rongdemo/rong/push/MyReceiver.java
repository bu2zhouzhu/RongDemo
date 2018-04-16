package com.example.bu2zh.rongdemo.rong.push;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.huawei.hms.support.api.push.PushReceiver;

/**
 * Created by luoyanlong on 2018/03/02.
 */

public class MyReceiver extends PushReceiver {

    private static final String TAG = "MyReceiver";

    @Override
    public boolean onPushMsg(Context context, byte[] bytes, Bundle bundle) {
        Log.d(TAG, "onPushMsg");
        return super.onPushMsg(context, bytes, bundle);
    }

    @Override
    public void onPushMsg(Context context, byte[] bytes, String s) {
        Log.d(TAG, "onPushMsg");
        super.onPushMsg(context, bytes, s);
    }
}
