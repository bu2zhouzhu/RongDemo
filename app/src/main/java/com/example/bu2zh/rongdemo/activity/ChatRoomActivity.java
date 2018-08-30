package com.example.bu2zh.rongdemo.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.widget.EditText;

import com.example.bu2zh.rongdemo.R;
import com.example.bu2zh.rongdemo.base.BaseActivity;
import com.example.bu2zh.rongdemo.utils.MyToast;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.RongIMClient;
import io.rong.push.RongPushClient;

public class ChatRoomActivity extends BaseActivity {

    @BindView(R.id.et)
    EditText mEt;

    @OnClick(R.id.create)
    void onCreateClick() {
        String chatroomId = mEt.getText().toString();
        if (TextUtils.isEmpty(chatroomId)) {
            MyToast.show("请输入聊天室id");
            return;
        }
        RongIM.getInstance().startChatRoomChat(this, chatroomId, true);
    }

    @OnClick(R.id.join)
    void onJoinClick() {
        final String chatroomId = mEt.getText().toString();
        if (TextUtils.isEmpty(chatroomId)) {
            MyToast.show("请输入聊天室id");
            return;
        }
        RongIM.getInstance().joinChatRoom(chatroomId, -1, new RongIMClient.OperationCallback() {
            @Override
            public void onSuccess() {
                RongIM.getInstance().startChatRoomChat(ChatRoomActivity.this, chatroomId, true);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                MyToast.show("加入聊天室失败: " + errorCode.getMessage());
            }
        });
    }

    @OnClick(R.id.test)
    void onTestClick() {
        String chatroomId = mEt.getText().toString();
        if (TextUtils.isEmpty(chatroomId)) {
            MyToast.show("请输入聊天室id");
            return;
        }
        ConversationFragment fragment = new ConversationFragment();
        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversation").appendPath(RongPushClient.ConversationType.CHATROOM.getName().toLowerCase())
                .appendQueryParameter("targetId", chatroomId).build();
        fragment.setUri(uri);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container, fragment);
        transaction.commit();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
    }
}
