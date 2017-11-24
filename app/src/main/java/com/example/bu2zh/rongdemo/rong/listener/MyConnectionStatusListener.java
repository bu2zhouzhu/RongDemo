package com.example.bu2zh.rongdemo.rong.listener;

import android.util.Log;

import io.rong.imlib.RongIMClient;

/**
 * 连接状态监听器
 */

public class MyConnectionStatusListener implements RongIMClient.ConnectionStatusListener {

    private static final String TAG = "连接状态监听器";

    @Override
    public void onChanged(ConnectionStatus connectionStatus) {
        Log.d(TAG, "连接状态改变");
    }

}
