package com.example.bu2zh.rongdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.bu2zh.rongdemo.sp.ConfigSp;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String token = new ConfigSp(this).getToken();
        if (TextUtils.isEmpty(token)) {
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            toMainActivity();
        }
        finish();
    }

    private void connect(String token) {
        // 连接服务器
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {

            }

            @Override
            public void onSuccess(String s) {
                toMainActivity();
                finish();
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Toast.makeText(SplashActivity.this, "连接失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void toMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
