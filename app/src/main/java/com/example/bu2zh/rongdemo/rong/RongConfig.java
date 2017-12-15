package com.example.bu2zh.rongdemo.rong;

import android.app.ActivityManager;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.example.bu2zh.rongdemo.rong.custom.message.CustomizeMessage;
import com.example.bu2zh.rongdemo.rong.custom.message.CustomizeMessageItemProvider;
import com.example.bu2zh.rongdemo.rong.custom.message.MyExtensionModule;
import com.example.bu2zh.rongdemo.rong.custom.message.MyTextMessageItemProvider;
import com.example.bu2zh.rongdemo.rong.custom.ui.conversationlist.MySystemConversationProvider;
import com.example.bu2zh.rongdemo.rong.listener.MyConnectionStatusListener;
import com.example.bu2zh.rongdemo.rong.listener.MyConversationBehaviorListener;
import com.example.bu2zh.rongdemo.rong.listener.MyConversationListBehaviorListener;
import com.example.bu2zh.rongdemo.rong.listener.MyReceiveMessageListener;
import com.example.bu2zh.rongdemo.rong.listener.MySendMessageListener;

import java.util.List;

import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.IExtensionModule;
import io.rong.imkit.RongExtensionManager;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;
import io.rong.push.RongPushClient;

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
            UserInfo userinfo = new UserInfo(s, s, uri);
            // 刷新用户信息
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
        // OnCreate 会被多个进程重入，这段保护代码，确保只有您需要使用 RongIMClient 的进程执行了 init。
        if (context.getApplicationInfo().packageName.equals(getCurProcessName(context.getApplicationContext()))) {
            getInstance().config(context);
            Log.d(TAG, getCurProcessName(context.getApplicationContext()) + " init");
        }
    }

    private void config(Context context) {

        RongPushClient.registerHWPush(context);

        RongIM.init(context);

        // 自定义会话列表
        RongIM.getInstance().registerConversationTemplate(new MySystemConversationProvider());

        // 注册自定义消息
        RongIM.registerMessageType(CustomizeMessage.class);
        RongIM.registerMessageTemplate(new CustomizeMessageItemProvider());
        RongIM.registerMessageTemplate(new MyTextMessageItemProvider());
        setMyExtensionModule();

        // 注册监听器
        initListener();

        RongIM.getInstance().enableNewComingMessageIcon(true); //显示新消息提醒
        RongIM.getInstance().enableUnreadMessageIcon(true); //显示未读消息数目

        enableReadReceipt();
    }

    private void initListener() {
        // 发送消息监听器
        RongIM.getInstance().setSendMessageListener(new MySendMessageListener());

        // 接收消息监听器
        RongIM.setOnReceiveMessageListener(new MyReceiveMessageListener());

        // 连接状态监听器
        RongIM.setConnectionStatusListener(new MyConnectionStatusListener());

        // 会话列表操作监听器
        RongIM.setConversationListBehaviorListener(new MyConversationListBehaviorListener());

        // 会话界面操作监听器
        RongIM.setConversationBehaviorListener(new MyConversationBehaviorListener());

        // 设置用户信息提供者
        RongIM.setUserInfoProvider(mUserInfoProvider, true);
    }

    private void enableReadReceipt() {
        Conversation.ConversationType[] types = new Conversation.ConversationType[] {
                Conversation.ConversationType.PRIVATE,
                Conversation.ConversationType.GROUP,
                Conversation.ConversationType.DISCUSSION
        };
        RongIM.getInstance().setReadReceiptConversationTypeList(types);
    }

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
