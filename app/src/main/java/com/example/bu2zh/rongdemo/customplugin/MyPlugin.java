package com.example.bu2zh.rongdemo.customplugin;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;

import com.example.bu2zh.rongdemo.R;
import com.example.bu2zh.rongdemo.custommessage.CustomizeMessage;

import io.rong.imkit.RongExtension;
import io.rong.imkit.RongIM;
import io.rong.imkit.plugin.IPluginModule;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;

public class MyPlugin implements IPluginModule {

    private IRongCallback.ISendMessageCallback callback = new IRongCallback.ISendMessageCallback() {
        @Override
        public void onAttached(Message message) {

        }

        @Override
        public void onSuccess(Message message) {

        }

        @Override
        public void onError(Message message, RongIMClient.ErrorCode errorCode) {

        }
    };

    @Override
    public Drawable obtainDrawable(Context context) {
        return context.getResources().getDrawable(R.mipmap.ic_launcher);
    }

    @Override
    public String obtainTitle(Context context) {
        return "MyPlugin";
    }

    @Override
    public void onClick(Fragment fragment, RongExtension rongExtension) {
        CustomizeMessage content = new CustomizeMessage("CustomMessage");
        String targetId = rongExtension.getTargetId();
        Message message = Message.obtain(targetId, Conversation.ConversationType.PRIVATE, content);
        RongIM.getInstance().sendMessage(message, null, null, callback);
    }

    @Override
    public void onActivityResult(int i, int i1, Intent intent) {

    }
}
