package com.example.bu2zh.rongdemo.rong.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.bu2zh.rongdemo.sp.ConfigSp;

import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;
import io.rong.message.InformationNotificationMessage;

public class MyConversationFragment extends ConversationFragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MessageContent content = InformationNotificationMessage.obtain(
                "小灰条测试"
        );
        String targetId = getUri().getQueryParameter("targetId");
        String myId = new ConfigSp(getContext()).getId();
        RongIM.getInstance().insertIncomingMessage(
                Conversation.ConversationType.PRIVATE,
                targetId,
                myId,
                new Message.ReceivedStatus(1),
                content,
                new RongIMClient.ResultCallback<Message>() {
                    @Override
                    public void onSuccess(Message message) {

                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {

                    }
                }
        );
    }
}
