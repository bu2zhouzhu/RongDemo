package com.example.bu2zh.rongdemo.rong;

import android.app.ActivityManager;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.example.bu2zh.rongdemo.rong.message.CustomizeMessage;
import com.example.bu2zh.rongdemo.rong.message.CustomizeMessageItemProvider;
import com.example.bu2zh.rongdemo.rong.plugin.MyExtensionModule;

import java.util.List;

import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.IExtensionModule;
import io.rong.imkit.RongExtensionManager;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;

/**
 * 融云SDK配置类
 */
public class RongConfig {
    private static final String TAG = "RongConfig";

    private static RongConfig sRongConfig = new RongConfig();

    private RongIM.UserInfoProvider mUserInfoProvider = new RongIM.UserInfoProvider() {
        @Override
        public UserInfo getUserInfo(String s) {
            Uri uri = Uri.parse("http://rongcloud-web.qiniudn.com/docs_demo_rongcloud_logo.png");
            UserInfo userinfo = new UserInfo(s, "test", uri);
            RongIM.getInstance().refreshUserInfoCache(userinfo);
            return userinfo;
        }
    };

    private RongConfig() {
    }

    private static RongConfig getInstance() {
        return sRongConfig;
    }

    public static void init(Context context) {
        getInstance().config(context);
    }

    private void config(Context context) {

        // OnCreate 会被多个进程重入，这段保护代码，确保只有您需要使用 RongIMClient 的进程和 Push 进程执行了 init。
        // io.rong.push 为融云 push 进程名称，不可修改。
        if (context.getApplicationInfo().packageName.equals(getCurProcessName(context.getApplicationContext())) ||
                "io.rong.push".equals(getCurProcessName(context.getApplicationContext()))) {
            RongIM.init(context);
        }

        // 注册自定义消息
        RongIM.registerMessageType(CustomizeMessage.class);
        RongIM.registerMessageTemplate(new CustomizeMessageItemProvider());
        setMyExtensionModule();

        // 注册监听器
        initListener();

        RongIM.getInstance().enableNewComingMessageIcon(true);
        RongIM.getInstance().enableUnreadMessageIcon(true);

    }

    private void initListener() {
        // 接收消息监听器
        RongIM.setOnReceiveMessageListener(onReceiveMessageListener);

        // 设置用户信息提供者
        RongIM.setUserInfoProvider(mUserInfoProvider, true);
    }

    private RongIMClient.OnReceiveMessageListener onReceiveMessageListener = new RongIMClient.OnReceiveMessageListener() {
        @Override
        public boolean onReceived(Message message, int i) {
            Log.d(TAG, "收到消息");
            return false;
        }
    };

    private static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid)
                return appProcess.processName;
        }
        return null;
    }

    private void setMyExtensionModule() {
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
