package com.example.bu2zh.rongdemo;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;

public class AppContext {
    private static final String TAG = "AppContext";
    private int mMessageId;

    private static AppContext appContext = new AppContext();
    private Context mContext;

    private RongIM.UserInfoProvider mUserInfoProvider = new RongIM.UserInfoProvider() {
        @Override
        public UserInfo getUserInfo(String s) {
            Uri uri = Uri.parse("http://rongcloud-web.qiniudn.com/docs_demo_rongcloud_logo.png");
            UserInfo userinfo = new UserInfo(s, "test", uri);
            RongIM.getInstance().refreshUserInfoCache(userinfo);
            return userinfo;
        }
    };

    private AppContext() {
        initListener();
    }

    static AppContext getInstance() {
        return appContext;
    }

    public int getMessageId() {
        return mMessageId;
    }

    private void setMessageId(int mMessageId) {
        this.mMessageId = mMessageId;
    }

    void init(Context context) {
        mContext = context.getApplicationContext();
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

    /**
     * 设置消息为已读消息
     */
    private void setMessageRead(Message message) {
        if (message.getMessageId() > 0) {
            io.rong.imlib.model.Message.ReceivedStatus status = message.getReceivedStatus();
            status.setRead();
            message.setReceivedStatus(status);
            RongIMClient.getInstance().setMessageReceivedStatus(message.getMessageId(), status, null);
            Toast.makeText(mContext, "该条消息已设置为已读", Toast.LENGTH_LONG).show();
        }
    }
}
