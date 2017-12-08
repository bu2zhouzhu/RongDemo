package com.example.bu2zh.rongdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.example.bu2zh.rongdemo.R;
import com.example.bu2zh.rongdemo.base.BaseActivity;
import com.example.bu2zh.rongdemo.rong.activity.ConversationListActivity;
import com.example.bu2zh.rongdemo.sp.ConfigSp;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.CSCustomServiceInfo;
import io.rong.imlib.model.Conversation;

public class MainActivity extends BaseActivity {

    @BindView(R.id.me)
    TextView mMe;

    @OnClick(R.id.conversation_list)
    void onConversationListClick() {
//        Map<String, Boolean> supportedConversation = new HashMap<>();
//        supportedConversation.put(Conversation.ConversationType.PRIVATE.getName(), false);
//        supportedConversation.put(Conversation.ConversationType.SYSTEM.getName(), false);
//        supportedConversation.put(Conversation.ConversationType.DISCUSSION.getName(), false);
//        // 启动会话列表界面
//        RongIM.getInstance().startConversationList(this, supportedConversation);

        startActivity(new Intent(this, ConversationListActivity.class));
    }

    @OnClick(R.id.discuss_chat)
    void onDiscussClick() {
        startActivity(new Intent(this, DiscussActivity.class));
    }

    @OnClick(R.id.custom_service)
    void onCustomServiceClick() {
        CSCustomServiceInfo.Builder csBuilder = new CSCustomServiceInfo.Builder();
        CSCustomServiceInfo csInfo = csBuilder.nickName("融云").build();
        String id = "KEFU151150386626795";
        // 打开客服聊天界面
        RongIM.getInstance().startCustomerServiceChat(this, id, "在线客服",csInfo);
    }

    @OnClick(R.id.refresh_user_info)
    void onRefreshUserInfoClick() {
        startActivity(new Intent(this, RefreshUserInfoActivity.class));
    }

    @OnClick(R.id.logout)
    void onLogoutClick() {
        // 断开连接
        RongIM.getInstance().logout();
        new ConfigSp(this).clear();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @OnClick(R.id.send_message)
    void onSendMessageClick() {
        startActivity(new Intent(this, SendMessageActivity.class));
    }

    @OnClick(R.id.group)
    void onGroupClick() {
        startActivity(new Intent(this, GroupActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String id = new ConfigSp(this).getId();
        mMe.setText(getString(R.string.my_id, id));

        RongIM.getInstance().getUnreadCount(Conversation.ConversationType.PRIVATE, "9", new RongIMClient.ResultCallback<Integer>() {
            @Override
            public void onSuccess(Integer integer) {
                Log.d("mmm", "count: " + integer);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.d("mmm", "error: " + errorCode.getMessage());
            }
        });
    }
}
