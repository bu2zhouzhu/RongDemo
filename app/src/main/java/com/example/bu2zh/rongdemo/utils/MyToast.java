package com.example.bu2zh.rongdemo.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by yanlongluo on 23/11/2017.
 */

public class MyToast {

    private static Context sContext;

    public static void init(Context context) {
        sContext = context;
    }

    public static void show(String text) {
        Toast.makeText(sContext, text, Toast.LENGTH_SHORT).show();
    }
}
