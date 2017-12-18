package com.example.bu2zh.rongdemo.rong.activity;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.example.bu2zh.rongdemo.R;
import com.example.bu2zh.rongdemo.base.BaseActivity;

import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imkit.manager.IUnReadMessageObserver;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

/**
 * 配置会话列表
 */

public class ConversationListActivity extends BaseActivity {

    private static final String TAG = "会话列表界面";
    private boolean b;

    private IUnReadMessageObserver mObserver = new IUnReadMessageObserver() {
        @Override
        public void onCountChanged(int i) {
            Log.d(TAG, "未读消息数： " + i);
        }
    };

    @OnClick(R.id.btn)
    void onClick() {
        String avatar;
        if (b) {
            int drawableId = R.drawable.test1;
            avatar = ContentResolver.SCHEME_ANDROID_RESOURCE +
                    "://" + getResources().getResourcePackageName(drawableId)
                    + '/' + getResources().getResourceTypeName(drawableId)
                    + '/' + getResources().getResourceEntryName(drawableId);
        } else {
            avatar = "http://desk.fd.zol-img.com.cn/t_s720x360c5/g5/M00/0D/0F/ChMkJ1nJyRyIe8zJANiwdGLom9sAAgysAMQPe8A2LCM092.jpg";
        }
        b = !b;
        Uri uri = Uri.parse(avatar);
        UserInfo userinfo = new UserInfo("9", "haha", uri);
        RongIM.getInstance().refreshUserInfoCache(userinfo);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversationlist);

        ConversationListFragment fragment = new MyConversationListFragment();

        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话是否聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//群组
                .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")
                .build();
        fragment.setUri(uri);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container, fragment);
        transaction.commit();

        final Conversation.ConversationType[] conversationTypes = {
                Conversation.ConversationType.PRIVATE,
                Conversation.ConversationType.GROUP,
                Conversation.ConversationType.SYSTEM,
                Conversation.ConversationType.PUBLIC_SERVICE, Conversation.ConversationType.APP_PUBLIC_SERVICE
        };
        RongIM.getInstance().addUnReadMessageCountChangedObserver(mObserver, conversationTypes);
    }
}
