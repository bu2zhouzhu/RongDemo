package com.example.bu2zh.rongdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.bu2zh.rongdemo.R;
import com.example.bu2zh.rongdemo.base.BaseActivity;
import com.example.bu2zh.rongdemo.utils.MyToast;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;
import io.rong.message.CommandMessage;
import io.rong.message.TextMessage;

public class SendMessageActivity extends BaseActivity {

    @BindView(R.id.target_id_et)
    EditText mTargetIdEt;

    @OnClick({R.id.send_text_message, R.id.send_command_message})
    void onSendMessageClick(View v) {
        if (!isValid()) {
            MyToast.show("请输入用户id");
            return;
        }
        MessageContent content = null;
        switch (v.getId()) {
            case R.id.send_text_message:
                content = TextMessage.obtain("hi");
                break;
            case R.id.send_command_message:
                content = CommandMessage.obtain("cmdName", "cmdData");
                break;
        }
        String targetId = mTargetIdEt.getText().toString();
        Conversation.ConversationType conversationType = Conversation.ConversationType.PRIVATE;
        Message message = Message.obtain(targetId, conversationType, content);
        RongIM.getInstance().sendMessage(message, null, null, new IRongCallback.ISendMessageCallback() {
            @Override
            public void onAttached(Message message) {

            }

            @Override
            public void onSuccess(Message message) {

            }

            @Override
            public void onError(Message message, RongIMClient.ErrorCode errorCode) {

            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
    }

    private boolean isValid() {
        return !TextUtils.isEmpty(mTargetIdEt.getText());
    }
}
