package com.example.bu2zh.rongdemo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;

import com.example.bu2zh.model.SimpleResponse;
import com.example.bu2zh.rongdemo.R;
import com.example.bu2zh.rongdemo.base.BaseActivity;
import com.example.bu2zh.rongdemo.sp.ConfigSp;
import com.example.bu2zh.rongdemo.utils.Constants;
import com.example.bu2zh.rongdemo.utils.MyToast;
import com.example.bu2zh.rongdemo.utils.SHA1Tool;
import com.example.network.ApiService;
import com.example.network.RongApi;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.rong.imkit.RongIM;

public class GroupActivity extends BaseActivity {

    private Context mContext;

    @BindView(R.id.group_id)
    EditText mGroupIdEt;
    @BindView(R.id.group_name)
    EditText mGroupNameEt;

    @OnClick(R.id.start_group_chat)
    void onStartGroupChatClick() {
        String groupId = mGroupIdEt.getText().toString();
        String groupName = mGroupNameEt.getText().toString();
        if (TextUtils.isEmpty(groupId)) {
            MyToast.show("请输入群组id");
            return;
        }
        if (TextUtils.isEmpty(groupName)) {
            groupName = groupId;
        }
        RongIM.getInstance().startGroupChat(mContext, groupId, groupName);
    }

    @OnClick(R.id.join_group)
    void onJoinGroupClick() {
        final String nonce = Integer.toString(new Random().nextInt(1000));
        final String timeStamp = Long.toString(System.currentTimeMillis());
        final String signature = SHA1Tool.SHA1(Constants.APP_SECRET + nonce + timeStamp);

        RongApi api = ApiService.getInstance().create(RongApi.class);
        Map<String, String> headers = new HashMap<>();
        headers.put("App-Key", Constants.APP_KEY);
        headers.put("Nonce", nonce);
        headers.put("Timestamp", timeStamp);
        headers.put("Signature", signature);

        String userId = new ConfigSp(this).getId();
        final String groupId = mGroupIdEt.getText().toString();
        if (TextUtils.isEmpty(groupId)) {
            MyToast.show("请输入群组id");
            return;
        }

        api.joinGroup(headers, userId, groupId, groupId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<SimpleResponse>() {
                            @Override
                            public void accept(SimpleResponse tokenResponse) throws Exception {
                                RongIM.getInstance().startGroupChat(mContext, groupId, groupId);
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                throwable.printStackTrace();
                            }
                        }
                );
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        mContext = this;
//
//        GroupUserInfo groupUserInfo = new GroupUserInfo()
//        RongIM.getInstance().refreshGroupUserInfoCache();
    }
}
