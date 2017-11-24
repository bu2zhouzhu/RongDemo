package com.example.bu2zh.rongdemo.rong;

import android.net.Uri;
import android.util.Log;

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
        initListener();
        RongIM.getInstance().enableNewComingMessageIcon(true);
        RongIM.getInstance().enableUnreadMessageIcon(true);
    }

    public static RongConfig getInstance() {
        return sRongConfig;
    }

    public void init() {

    }

    private void initListener() {
        RongIM.setOnReceiveMessageListener(onReceiveMessageListener);
        RongIM.setUserInfoProvider(mUserInfoProvider, true);
    }

    /**
     * 设置接收消息的监听器
     */
    private RongIMClient.OnReceiveMessageListener onReceiveMessageListener = new RongIMClient.OnReceiveMessageListener() {
        @Override
        public boolean onReceived(Message message, int i) {
            Log.d(TAG, "收到消息");
            return false;
        }
    };
}
