package com.example.bu2zh.rongdemo;

import android.app.Application;

import io.rong.imkit.RongIM;

/**
 * Created by bu2zh on 2017/11/15.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RongIM.init(this);
    }
}
