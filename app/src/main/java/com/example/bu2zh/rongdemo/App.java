package com.example.bu2zh.rongdemo;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.example.bu2zh.rongdemo.rong.message.CustomizeMessage;
import com.example.bu2zh.rongdemo.rong.message.CustomizeMessageItemProvider;
import com.example.bu2zh.rongdemo.rong.plugin.MyExtensionModule;
import com.example.bu2zh.rongdemo.utils.MyToast;

import java.util.List;

import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.IExtensionModule;
import io.rong.imkit.RongExtensionManager;
import io.rong.imkit.RongIM;
import io.rong.imlib.AnnotationNotFoundException;
import io.rong.imlib.RongIMClient;

/**
 * Created by bu2zh on 2017/11/15.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        /*
         * OnCreate 会被多个进程重入，这段保护代码，确保只有您需要使用 RongIMClient 的进程和 Push 进程执行了 init。
         * io.rong.push 为融云 push 进程名称，不可修改。
         */
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext())) ||
                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {
            RongIM.init(this);
        }

        /*
         * 用于自定义消息的注册, 注册后方能正确识别自定义消息, 建议在init后及时注册，保证自定义消息到达时能正确解析。
         */
        try {
            RongIMClient.registerMessageType(CustomizeMessage.class);
        } catch (AnnotationNotFoundException e) {
            e.printStackTrace();
        }

        RongIM.registerMessageTemplate(new CustomizeMessageItemProvider());

        setMyExtensionModule();

        AppContext.getInstance().init(getApplicationContext());

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
