package com.example.bu2zh.rongdemo.rong.listener;

import android.util.Log;

import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;
import io.rong.message.CommandMessage;

/**
 * 接收消息监听器
 */

public class MyReceiveMessageListener implements RongIMClient.OnReceiveMessageListener {

    private static final String TAG = "接收消息监听器";

    @Override
    public boolean onReceived(Message message, int i) {
        MessageContent content = message.getContent();
        if (content instanceof CommandMessage) {
            Log.d(TAG, "收到 CommandMessage");
        } else {
            Log.d(TAG, "收到消息");
        }
        return false;
    }
}
