package com.example.bu2zh.rongdemo.rong.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.CompoundButton;

import com.example.bu2zh.rongdemo.R;
import com.example.bu2zh.rongdemo.base.BaseActivity;
import com.example.bu2zh.rongdemo.utils.MyToast;

import butterknife.OnCheckedChanged;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

public class PrivateChatDetailActivity extends BaseActivity {

    private String mTargetId;

    @OnCheckedChanged(R.id.sw_friend_notification)
    void onNotifyChange(CompoundButton buttonView, boolean isChecked) {
        Conversation.ConversationNotificationStatus cns;
        if (isChecked) {
            cns = Conversation.ConversationNotificationStatus.DO_NOT_DISTURB;
        } else {
            cns = Conversation.ConversationNotificationStatus.NOTIFY;
        }
        RongIM.getInstance().setConversationNotificationStatus(Conversation.ConversationType.PRIVATE, mTargetId, cns, new RongIMClient.ResultCallback<Conversation.ConversationNotificationStatus>() {
            @Override
            public void onSuccess(Conversation.ConversationNotificationStatus conversationNotificationStatus) {
                if (conversationNotificationStatus == Conversation.ConversationNotificationStatus.DO_NOT_DISTURB) {
                    MyToast.show("设置免打扰成功");
                } else if (conversationNotificationStatus == Conversation.ConversationNotificationStatus.NOTIFY) {
                    MyToast.show("取消免打扰成功");
                }
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
    }
}
