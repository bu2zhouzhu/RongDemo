package com.example.bu2zh.rongdemo;

import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;
import android.util.Log;

import com.example.bu2zh.rongdemo.rong.RongConfig;
import com.example.bu2zh.rongdemo.sp.ConfigSp;
import com.example.bu2zh.rongdemo.utils.MyToast;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class App extends MultiDexApplication {

    private static final String TAG = "Apppp";

    @Override
    public void onCreate() {
        super.onCreate();
        RongConfig.init(this);
        String token = new ConfigSp(this).getToken();
        if (!TextUtils.isEmpty(token)) {
            connect(token);
        }
        MyToast.init(getApplicationContext());
        Log.d(TAG, "onCreate");
    }

    private void connect(String token) {
        // 连接服务器
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {

            }

            @Override
            public void onSuccess(String s) {
                Log.d(TAG, "connect success: " + s);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });
    }
}
