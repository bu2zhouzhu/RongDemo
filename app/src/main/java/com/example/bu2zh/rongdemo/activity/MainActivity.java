package com.example.bu2zh.rongdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.bu2zh.rongdemo.LoginActivity;
import com.example.bu2zh.rongdemo.R;
import com.example.bu2zh.rongdemo.base.BaseActivity;
import com.example.bu2zh.rongdemo.sp.ConfigSp;

import java.util.HashMap;
import java.util.Map;

import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

public class MainActivity extends BaseActivity {

    @OnClick(R.id.conversation_list)
    void onConversationListClick() {
        Map<String, Boolean> supportedConversation = new HashMap<>();
        supportedConversation.put(Conversation.ConversationType.PRIVATE.getName(), false);
        supportedConversation.put(Conversation.ConversationType.SYSTEM.getName(), false);
        supportedConversation.put(Conversation.ConversationType.DISCUSSION.getName(), false);
        RongIM.getInstance().startConversationList(this, supportedConversation);
    }

    @OnClick(R.id.discuss_chat)
    void onDiscussClick() {
        startActivity(new Intent(this, DiscussActivity.class));
    }

    @OnClick(R.id.logout)
    void onLogoutClick() {
        RongIM.getInstance().logout();
        new ConfigSp(this).saveToken(null);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
