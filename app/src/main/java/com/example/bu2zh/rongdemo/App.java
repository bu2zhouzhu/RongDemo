package com.example.bu2zh.rongdemo;

import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.example.bu2zh.rongdemo.rong.RongConfig;
import com.example.bu2zh.rongdemo.utils.MyToast;

public class App extends MultiDexApplication {

    private static final String TAG = "Apppp";

    @Override
    public void onCreate() {
        super.onCreate();
        RongConfig.init(this);
        MyToast.init(getApplicationContext());
        Log.d(TAG, "onCreate");
    }
}
