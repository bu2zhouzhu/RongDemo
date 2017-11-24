package com.example.bu2zh.rongdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.example.bu2zh.rongdemo.R;
import com.example.bu2zh.rongdemo.base.BaseActivity;
import com.example.bu2zh.rongdemo.utils.MyToast;

import java.util.Arrays;
import java.util.List;

import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class DiscussActivity extends BaseActivity {

    private String mDiscussionId;

    @OnClick(R.id.create_discussion)
    void onCreateDiscussionClick() {
        String name = "讨论组";
        List<String> userIdList = Arrays.asList("1", "2", "3", "4", "5");
        // 创建讨论组
        RongIM.getInstance().createDiscussion(name, userIdList, new RongIMClient.CreateDiscussionCallback() {
            @Override
            public void onSuccess(String s) {
                mDiscussionId = s;
                MyToast.show("创建讨论组成功 " + s);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                MyToast.show("创建讨论组失败");
            }
        });
    }

    @OnClick(R.id.start_discussion)
    void onStartDiscussionClick() {
        if (TextUtils.isEmpty(mDiscussionId)) {
            MyToast.show("请先创建讨论组");
            return;
        }
        // 打开讨论组聊天窗口
        RongIM.getInstance().startDiscussionChat(this, mDiscussionId, "讨论组");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discuss);
    }
}
