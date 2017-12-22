package com.example.bu2zh.rongdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;

import com.example.bu2zh.rongdemo.R;
import com.example.bu2zh.rongdemo.base.BaseActivity;
import com.example.bu2zh.rongdemo.utils.MyToast;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;

public class DiscussActivity extends BaseActivity {

    @BindView(R.id.discussion_name)
    EditText mDiscussionNameEt;
    @BindView(R.id.ids)
    EditText mIdsEt;

    @OnClick(R.id.create_discussion)
    void onCreateDiscussionClick() {
        final String name = mDiscussionNameEt.getText().toString();
        if (TextUtils.isEmpty(name)) {
            MyToast.show(R.string.discussion_name_hint);
            return;
        }
        String idString = mIdsEt.getText().toString();
        if (TextUtils.isEmpty(idString)) {
            MyToast.show(R.string.create_discussion_hint);
            return;
        }
        String[] ids = idString.split(",");
        List<String> userIdList = Arrays.asList(ids);
        // 创建讨论组
        RongIM.getInstance().createDiscussionChat(this, userIdList, name);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discuss);
    }
}
