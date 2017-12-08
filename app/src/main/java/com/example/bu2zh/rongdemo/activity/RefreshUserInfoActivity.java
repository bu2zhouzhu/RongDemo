package com.example.bu2zh.rongdemo.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;

import com.example.bu2zh.rongdemo.R;
import com.example.bu2zh.rongdemo.base.BaseActivity;
import com.example.bu2zh.rongdemo.sp.ConfigSp;
import com.example.bu2zh.rongdemo.utils.MyToast;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

public class RefreshUserInfoActivity extends BaseActivity {

    @BindView(R.id.id)
    EditText mIdEt;
    @BindView(R.id.name)
    EditText mNameEt;
    @BindView(R.id.avatar)
    EditText mAvatarEt;

    @OnClick(R.id.ok)
    void onClick() {
        String id = mIdEt.getText().toString();
        String name = mNameEt.getText().toString();
        String avatar = mAvatarEt.getText().toString();
        if (TextUtils.isEmpty(name)) {
            MyToast.show("请填写昵称");
            return;
        }
        if (TextUtils.isEmpty(avatar)) {
            MyToast.show("请填写头像链接");
            return;
        }
        if (TextUtils.isEmpty(id)) {
            id = new ConfigSp(this).getId();
        }
        Uri uri = Uri.parse(avatar);
        UserInfo userinfo = new UserInfo(id, name, uri);
        RongIM.getInstance().refreshUserInfoCache(userinfo);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_user_info);
    }
}
