package com.example.bu2zh.rongdemo.rong.listener;

import android.os.CountDownTimer;
import android.util.Log;

import java.lang.reflect.Field;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * 连接状态监听器
 */

public class MyConnectionStatusListener implements RongIMClient.ConnectionStatusListener {

    private static final String TAG = "连接状态监听器";

    @Override
    public void onChanged(ConnectionStatus connectionStatus) {
        Log.d(TAG, "连接状态改变: " + connectionStatus);
        if (connectionStatus == ConnectionStatus.CONNECTING) {
            CountDownTimer timer = new CountDownTimer(30000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    if (RongIM.getInstance().getCurrentConnectionStatus() == ConnectionStatus.CONNECTING) {
                        try {
                            Field field = RongIMClient.class.getDeclaredField("mConnectionStatus");
                            field.setAccessible(true);
                            field.set(RongIMClient.getInstance(), ConnectionStatus.NETWORK_UNAVAILABLE);
                        } catch (NoSuchFieldException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            timer.start();
        }
    }
}
