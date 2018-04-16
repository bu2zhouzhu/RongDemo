package com.example.bu2zh.rongdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.bu2zh.rongdemo.R;
import com.example.bu2zh.rongdemo.base.BaseActivity;
import com.example.bu2zh.rongdemo.utils.MyToast;

import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

public class GroupDetailActivity extends BaseActivity {

    private Conversation.ConversationType mConversationType;
    private String mTargetId;
    private boolean mState;

    @OnClick(R.id.disturb)
    void onDisturbClick() {
        Conversation.ConversationNotificationStatus status = Conversation.ConversationNotificationStatus.DO_NOT_DISTURB;
        if (mState) {
            status = Conversation.ConversationNotificationStatus.NOTIFY;
        }
        RongIM.getInstance().setConversationNotificationStatus(mConversationType, mTargetId, status, new RongIMClient.ResultCallback<Conversation.ConversationNotificationStatus>() {
            @Override
            public void onSuccess(Conversation.ConversationNotificationStatus conversationNotificationStatus) {
                MyToast.show("成功");
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });
        mState = !mState;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);

        mConversationType = (Conversation.ConversationType) getIntent().getSerializableExtra("conversationType");
        mTargetId = getIntent().getStringExtra("TargetId");
    }
}
