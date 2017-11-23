package com.example.bu2zh.rongdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.bu2zh.rongdemo.R;
import com.example.bu2zh.rongdemo.base.BaseActivity;

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
        RongIM.getInstance().startConversationList(this, supportedConversation);
    }

    @OnClick(R.id.discuss_chat)
    void onDiscussClick() {
        startActivity(new Intent(this, DiscussActivity.class));
    }

    @OnClick(R.id.group)
    void onGroupClick() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
