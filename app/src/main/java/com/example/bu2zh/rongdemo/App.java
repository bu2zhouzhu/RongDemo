package com.example.bu2zh.rongdemo;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.example.bu2zh.rongdemo.rong.RongConfig;
import com.example.bu2zh.rongdemo.rong.custom.message.MyExtensionModule;
import com.example.bu2zh.rongdemo.utils.MyToast;

import java.util.List;

import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.IExtensionModule;
import io.rong.imkit.RongExtensionManager;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RongConfig.init(this);
        MyToast.init(getApplicationContext());
    }

    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid)
                return appProcess.processName;
        }
        return null;
    }

    public void setMyExtensionModule() {
        List<IExtensionModule> moduleList = RongExtensionManager.getInstance().getExtensionModules();
        IExtensionModule defaultModule = null;
        if (moduleList != null) {
            for (IExtensionModule module : moduleList) {
                if (module instanceof DefaultExtensionModule) {
                    defaultModule = module;
                    break;
                }
            }
            if (defaultModule != null) {
                RongExtensionManager.getInstance().unregisterExtensionModule(defaultModule);
                RongExtensionManager.getInstance().registerExtensionModule(new MyExtensionModule());
            }
        }
    }
}
