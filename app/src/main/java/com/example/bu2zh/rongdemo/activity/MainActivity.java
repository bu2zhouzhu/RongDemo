package com.example.bu2zh.rongdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bu2zh.rongdemo.BuildConfig;
import com.example.bu2zh.rongdemo.R;
import com.example.bu2zh.rongdemo.base.BaseActivity;
import com.example.bu2zh.rongdemo.rong.activity.ConversationListActivity;
import com.example.bu2zh.rongdemo.sp.ConfigSp;
import com.example.bu2zh.rongdemo.utils.MyToast;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.CSCustomServiceInfo;
import io.rong.imlib.model.Message;

public class MainActivity extends BaseActivity {

    @BindView(R.id.package_name)
    TextView mPackageName;
    @BindView(R.id.me)
    TextView mMe;
    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.et)
    EditText mEt;

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
        new Message.ReceivedStatus(1);
    }

    @OnClick(R.id.refresh_user_info)
    void onRefreshUserInfoClick() {
        startActivity(new Intent(this, RefreshUserInfoActivity.class));
    }

    @OnClick(R.id.logout)
    void onLogoutClick() {
        logout();
    }

    @OnClick(R.id.send_message)
    void onSendMessageClick() {
        startActivity(new Intent(this, SendMessageActivity.class));
    }

    @OnClick(R.id.group)
    void onGroupClick() {
        startActivity(new Intent(this, GroupActivity.class));
    }

    @OnClick(R.id.chatroom)
    void onChatroomClick() {
        startActivity(new Intent(this, ChatRoomActivity.class));
    }

    @OnClick(R.id.setting)
    void onTestClick() {
        String id = RongIM.getInstance().getCurrentUserId();
        Log.d("ccc", "id: " + id);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIv.setImageResource(getApplicationInfo().icon);

        mPackageName.setText(BuildConfig.APPLICATION_ID);
        String id = new ConfigSp(this).getId();
        SpannableStringBuilder ss = new SpannableStringBuilder("abc");
        Drawable d = getResources().getDrawable(R.drawable.u1f3a4);
        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
        ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
        ss.setSpan(span, 2, 3, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        mMe.setText(getString(R.string.my_id, id));
//        mMe.setText(ss);

        RongIM.setConnectionStatusListener(new RongIMClient.ConnectionStatusListener() {
            @Override
            public void onChanged(ConnectionStatus connectionStatus) {
                if (connectionStatus == ConnectionStatus.KICKED_OFFLINE_BY_OTHER_CLIENT) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            MyToast.show("您的账号在另外一台设备上登录");
                            logout();
                        }
                    });
                }
            }
        });
        Log.d("cccc", "isDebugMode: " + isDebugMode(this));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("ccccc", "onNewIntent");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RongIM.setConnectionStatusListener(null);
//        RongIM.getInstance().disconnect();
    }

    private void logout() {
        RongIM.getInstance().logout();
//        new ConfigSp(this).clear();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private static boolean isDebugMode(Context context) {
        ApplicationInfo info = context.getApplicationInfo();
        return info != null && (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }
}
