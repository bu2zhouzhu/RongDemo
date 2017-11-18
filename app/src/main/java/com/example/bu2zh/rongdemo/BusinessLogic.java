package com.example.bu2zh.rongdemo;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

/**
 * Created by luoyl on 11/18/17.
 */

public class BusinessLogic {

    private static final String TAG = "BusinessLogic";
    private Context mContext;

    public BusinessLogic(Context context) {
        this.mContext = context;
    }

    public void connect(String token) {
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                Log.d(TAG, "ConnectCallback connect onTokenIncorrect");
            }

            @Override
            public void onSuccess(String s) {
                startConversationList();
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.d(TAG, "ConnectCallback connect onError-ErrorCode=" + errorCode);
            }
        });
    }

    public void startConversationList() {
        Map<String, Boolean> supportedConversation = new HashMap<>();
        supportedConversation.put(Conversation.ConversationType.PRIVATE.getName(), false);
        RongIM.getInstance().startConversationList(mContext, supportedConversation);
    }

}
