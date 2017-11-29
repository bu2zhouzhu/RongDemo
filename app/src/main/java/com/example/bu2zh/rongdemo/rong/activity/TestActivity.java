package com.example.bu2zh.rongdemo.rong.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.bu2zh.rongdemo.R;
import com.example.bu2zh.rongdemo.base.BaseActivity;
import com.example.bu2zh.rongdemo.rong.fragment.MyConversationFragment;

import butterknife.BindView;
import io.rong.imlib.model.Conversation;

public class TestActivity extends BaseActivity {

    @BindView(R.id.parent)
    ViewGroup mParent;
    @BindView(R.id.container)
    FrameLayout mContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        MyConversationFragment fragment = new MyConversationFragment();

        String targetId = getIntent().getData().getQueryParameter("targetId");
        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversation").appendPath(Conversation.ConversationType.GROUP.getName().toLowerCase())
                .appendQueryParameter("targetId", targetId).build();

        fragment.setUri(uri);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.add(R.id.container, fragment);
        transaction.commit();
    }
}
