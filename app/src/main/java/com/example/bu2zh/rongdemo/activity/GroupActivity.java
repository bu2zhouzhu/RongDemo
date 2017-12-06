package com.example.bu2zh.rongdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;

import com.example.bu2zh.rongdemo.R;
import com.example.bu2zh.rongdemo.base.BaseActivity;
import com.example.bu2zh.rongdemo.utils.MyToast;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;

public class GroupActivity extends BaseActivity {

    @BindView(R.id.group_id)
    EditText mGroupIdEt;
    @BindView(R.id.group_name)
    EditText mGroupNameEt;

    @OnClick(R.id.start_group_chat)
    void onStartGroupChatClick() {
        String groupId = mGroupIdEt.getText().toString();
        String groupName = mGroupNameEt.getText().toString();
        if (TextUtils.isEmpty(groupId)) {
            MyToast.show("请输入 group id");
            return;
        }
        if (TextUtils.isEmpty(groupName)) {
            MyToast.show("请输入 group name");
            return;
        }
        RongIM.getInstance().startGroupChat(this, groupId, groupName);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
//
//        GroupUserInfo groupUserInfo = new GroupUserInfo()
//        RongIM.getInstance().refreshGroupUserInfoCache();
    }
}
