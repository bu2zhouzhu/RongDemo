package com.example.bu2zh.rongdemo.rong.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.example.bu2zh.rongdemo.R;
import com.example.bu2zh.rongdemo.activity.GroupDetailActivity;
import com.example.bu2zh.rongdemo.base.BaseActivity;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imkit.fragment.UriFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.message.ImageMessage;
import io.rong.message.TextMessage;

/**
 * 配置会话界面
 */

public class ConversationActivity extends BaseActivity {

    private String mTargetId;
    private Conversation.ConversationType mConversationType;
    private String[] nicks = {"nick1", "nick2", "nick3"};
    private int nickIndex;

    @BindView(R.id.btn_right)
    Button mRightButton;

    @OnClick(R.id.btn_left)
    void onBtnLeftClick() {
        finish();
    }

    @OnClick(R.id.text_right)
    void onTestClick() {
        RongIM.getInstance().getLatestMessages(mConversationType, mTargetId, 1, new RongIMClient.ResultCallback<List<Message>>() {
            @Override
            public void onSuccess(List<Message> messages) {
                Message message = messages.get(0);
                if (message.getContent() instanceof TextMessage) {
                    Log.d("cc", "文本消息");
                } else if (message.getContent() instanceof ImageMessage) {
                    Log.d("cc", "图片消息");
                }
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.d("cccc", "onError");
            }
        });
    }

    @OnClick(R.id.btn_right)
    void onBtnRightClick() {
        UriFragment fragment = (UriFragment) getSupportFragmentManager().getFragments().get(0);
        mTargetId = fragment.getUri().getQueryParameter("targetId");

        Intent intent = null;
        if (mConversationType == Conversation.ConversationType.PRIVATE) {
            intent = new Intent(this, PrivateChatDetailActivity.class);
            intent.putExtra("conversationType", Conversation.ConversationType.PRIVATE);
        } else if (mConversationType == Conversation.ConversationType.GROUP) {
            intent = new Intent(this, GroupDetailActivity.class);
            intent.putExtra("conversationType", Conversation.ConversationType.GROUP);
        }
        if (intent != null) {
            intent.putExtra("TargetId", mTargetId);
            startActivity(intent);
        }
    }

    @Override
    @TargetApi(23)
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        Uri uri = getIntent().getData();
        if (uri != null) {
            mTargetId = uri.getQueryParameter("targetId");
            mConversationType = Conversation.ConversationType.valueOf(uri
                    .getLastPathSegment().toUpperCase(Locale.US));

            if (mConversationType.equals(Conversation.ConversationType.SYSTEM)) {
                RongIM.getInstance().clearMessagesUnreadStatus(mConversationType, mTargetId, null);
                startActivity(new Intent(this, TestPushActivity.class));
                finish();
                return;
            }

            if (mConversationType.equals(Conversation.ConversationType.GROUP)) {
                mRightButton.setBackground(getResources().getDrawable(R.drawable.icon2_menu));
            } else if (mConversationType.equals(Conversation.ConversationType.PRIVATE) | mConversationType.equals(Conversation.ConversationType.PUBLIC_SERVICE) | mConversationType.equals(Conversation.ConversationType.DISCUSSION)) {
                mRightButton.setBackground(getResources().getDrawable(R.drawable.icon1_menu));
            } else {
                mRightButton.setVisibility(View.GONE);
                mRightButton.setClickable(false);
            }

            Log.d("ccccc", "is from push: " + uri.getQueryParameter("isFromPush"));

        }

    }

    @Override
    public void onBackPressed() {
        ConversationFragment fragment = (ConversationFragment) getSupportFragmentManager().findFragmentById(R.id.conversation);
        if (!fragment.onBackPressed()) {
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}
