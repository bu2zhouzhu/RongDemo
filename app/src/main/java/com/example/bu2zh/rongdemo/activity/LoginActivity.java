package com.example.bu2zh.rongdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bu2zh.model.TokenResponse;
import com.example.bu2zh.rongdemo.R;
import com.example.bu2zh.rongdemo.sp.ConfigSp;
import com.example.bu2zh.rongdemo.utils.MyToast;
import com.example.network.ApiService;
import com.example.network.RongApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_user_id)
    EditText mEtUserId;

    @OnClick(R.id.btn_login)
    void onLoginClick() {
        if (TextUtils.isEmpty(mEtUserId.getText().toString())) {
            Toast.makeText(this, "请填写用户id", Toast.LENGTH_SHORT).show();
            return;
        }
        String userId = mEtUserId.getText().toString();
        String userName = "test" + userId;
        String portrait = "https://developer.rongcloud.cn/static/imgs/logo_1.png";
        getToken(userId, userName, portrait);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    private void getToken(final String userId, String userName, String portrait) {
        RongApi api = ApiService.getInstance().create(RongApi.class);
        api.getToken(userId, userName, portrait)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<TokenResponse>() {
                            @Override
                            public void accept(TokenResponse tokenResponse) throws Exception {
                                ConfigSp sp = new ConfigSp(LoginActivity.this);
                                sp.setToken(tokenResponse.token());
                                sp.setId(userId);
                                connect(tokenResponse.token());
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

    private void connect(String token) {
        // 连接服务器
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                MyToast.show("token错误");
            }

            @Override
            public void onSuccess(String s) {
                toMainActivity();
                finish();
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Toast.makeText(LoginActivity.this, "连接失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void toMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
