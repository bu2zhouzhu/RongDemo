package com.example.bu2zh.rongdemo.rong.listener;

import android.util.Log;

import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;

/**
 * 接收消息监听器
 */

public class MyReceiveMessageListener implements RongIMClient.OnReceiveMessageListener {

    private static final String TAG = "接收消息监听器";

    @Override
    public boolean onReceived(Message message, int i) {
        Log.d(TAG, "收到消息");
        return false;
    }
}
