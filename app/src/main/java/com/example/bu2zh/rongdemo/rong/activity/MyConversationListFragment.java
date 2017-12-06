package com.example.bu2zh.rongdemo.rong.activity;

import java.util.List;

import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imkit.fragment.IHistoryDataResultCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

public class MyConversationListFragment extends ConversationListFragment {

    @Override
    public void getConversationList(Conversation.ConversationType[] conversationTypes, final IHistoryDataResultCallback<List<Conversation>> callback) {
        RongIMClient.getInstance().getConversationList(new RongIMClient.ResultCallback<List<Conversation>>() {
            public void onSuccess(List<Conversation> conversations) {
                if(callback != null) {
                    Conversation conversation = conversations.get(1);
                    conversation.setUnreadMessageCount(30);
                    callback.onResult(conversations);
                }
            }

            public void onError(RongIMClient.ErrorCode e) {
                if(callback != null) {
                    callback.onError();
                }
            }
        }, conversationTypes);
    }
}
