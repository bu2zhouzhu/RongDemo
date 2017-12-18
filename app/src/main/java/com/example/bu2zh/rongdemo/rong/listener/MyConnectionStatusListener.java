package com.example.bu2zh.rongdemo.rong.listener;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.bu2zh.rongdemo.utils.MyToast;

import io.rong.imlib.RongIMClient;

/**
 * 连接状态监听器
 */

public class MyConnectionStatusListener implements RongIMClient.ConnectionStatusListener {

    private static final String TAG = "连接状态监听器";

    @Override
    public void onChanged(ConnectionStatus connectionStatus) {
        Log.d(TAG, "连接状态改变: " + connectionStatus);
        if (connectionStatus == ConnectionStatus.KICKED_OFFLINE_BY_OTHER_CLIENT) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    MyToast.show("您的账号在另外一台设备上登录");
                }
            });
        }
    }

}
