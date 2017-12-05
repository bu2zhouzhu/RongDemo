package com.example.bu2zh.rongdemo.rong.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.bu2zh.rongdemo.R;
import com.example.bu2zh.rongdemo.base.BaseActivity;
import com.example.bu2zh.rongdemo.utils.MyToast;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

public class PrivateChatDetailActivity extends BaseActivity {

    private String mTargetId;
    private static final Conversation.ConversationType sConversationType = Conversation.ConversationType.PRIVATE;

    @BindView(R.id.sw_friend_notification)
    Switch mNotifySwitch;
    @BindView(R.id.sw_friend_top)
    Switch mTopSwitch;

    @OnCheckedChanged(R.id.sw_friend_notification)
    void onNotifyChange(CompoundButton buttonView, boolean isChecked) {
        Conversation.ConversationNotificationStatus cns;
        if (isChecked) {
            cns = Conversation.ConversationNotificationStatus.DO_NOT_DISTURB;
        } else {
            cns = Conversation.ConversationNotificationStatus.NOTIFY;
        }
        RongIM.getInstance().setConversationNotificationStatus(sConversationType, mTargetId, cns, new RongIMClient.ResultCallback<Conversation.ConversationNotificationStatus>() {
            @Override
            public void onSuccess(Conversation.ConversationNotificationStatus conversationNotificationStatus) {

            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                MyToast.show("设置失败");
            }
        });
    }

    @OnCheckedChanged(R.id.sw_friend_top)
    void onTopChange(CompoundButton buttonView, boolean isChecked) {
        RongIM.getInstance().setConversationToTop(sConversationType, mTargetId, isChecked, new RongIMClient.ResultCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {

            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                MyToast.show("设置失败");
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_chat);

        mTargetId = getIntent().getStringExtra("TargetId");
        RongIM.getInstance().getConversationNotificationStatus(sConversationType, mTargetId, new RongIMClient.ResultCallback<Conversation.ConversationNotificationStatus>() {
            @Override
            public void onSuccess(Conversation.ConversationNotificationStatus conversationNotificationStatus) {
                if (conversationNotificationStatus == Conversation.ConversationNotificationStatus.DO_NOT_DISTURB) {
                    mNotifySwitch.setChecked(true);
                } else {
                    mNotifySwitch.setChecked(false);
                }
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });
        RongIM.getInstance().getConversation(sConversationType, mTargetId, new RongIMClient.ResultCallback<Conversation>() {
            @Override
            public void onSuccess(Conversation conversation) {
                if (conversation.isTop()) {
                    mTopSwitch.setChecked(true);
                } else {
                    mTopSwitch.setChecked(false);
                }
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });
    }
}
