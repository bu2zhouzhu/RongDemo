package com.example.bu2zh.rongdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.example.bu2zh.rongdemo.sp.ConfigSp;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String token = new ConfigSp(this).getToken();
        if (TextUtils.isEmpty(token)) {
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            new BusinessLogic(this).connect(token);
        }
    }
}
