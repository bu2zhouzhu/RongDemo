package com.example.bu2zh.rongdemo.rong.listener;

import android.util.Log;

import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;
import io.rong.message.ImageMessage;

/**
 * 接收消息监听器
 */

public class MyReceiveMessageListener implements RongIMClient.OnReceiveMessageListener {

    private static final String TAG = "接收消息监听器";

    @Override
    public boolean onReceived(Message message, int i) {
        Log.d(TAG, "收到消息");
        message.getContent().getUserInfo()
        if (message.getContent() instanceof  ImageMessage) {
            ImageMessage imageMessage = (ImageMessage) message.getContent();
            Log.d("cccc", "remoteUri: " + imageMessage.getRemoteUri().toString());
        }
        return false;
    }
}
