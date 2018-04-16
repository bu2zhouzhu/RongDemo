package com.example.bu2zh.rongdemo.rong.push;

import android.content.Context;
import android.util.Log;

import io.rong.push.notification.PushMessageReceiver;
import io.rong.push.notification.PushNotificationMessage;

/**
 * 自定义广播接收器 / Push 消息监听器
 * 当您的应用处于后台运行或者和融云服务器 disconnect() 的时候，如果收到消息，融云 SDK 会以通知形式提醒您
 */
public class RongNotificationReceiver extends PushMessageReceiver {
    @Override
    public boolean onNotificationMessageArrived(Context context, PushNotificationMessage pushNotificationMessage) {
        Log.d("Rong-Push", "推送");
        return true;
    }

    @Override
    public boolean onNotificationMessageClicked(Context context, PushNotificationMessage pushNotificationMessage) {
        Log.d("Rong-Push", "点击通知栏");
        return false;
    }
}
