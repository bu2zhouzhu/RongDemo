package com.example.bu2zh.rongdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bu2zh.model.TokenResponse;
import com.example.bu2zh.rongdemo.utils.Constants;
import com.example.bu2zh.rongdemo.utils.SHA1Tool;
import com.example.network.ApiService;
import com.example.network.RongApi;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";

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
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    private void getToken(String userId, String userName, String portrait) {
        final String nonce = Integer.toString(new Random().nextInt(1000));
        final String timeStamp = Long.toString(System.currentTimeMillis());
        final String signature = SHA1Tool.SHA1(Constants.APP_SECRET + nonce + timeStamp);

        RongApi api = ApiService.getInstance().create(RongApi.class);
        Map<String, String> headers = new HashMap<>();
        headers.put("App-Key", Constants.APP_KEY);
        headers.put("Nonce", nonce);
        headers.put("Timestamp", timeStamp);
        headers.put("Signature", signature);

        api.getToken(headers, userId, userName, portrait)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<TokenResponse>() {
                            @Override
                            public void accept(TokenResponse tokenResponse) throws Exception {
                                Log.d(TAG, "token: " + tokenResponse.token());
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
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                Log.d(TAG, "ConnectCallback connect onTokenIncorrect");
            }

            @Override
            public void onSuccess(String s) {
                Log.d(TAG, "ConnectCallback connect onSuccess " + s);
                startConversationList();
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.d(TAG, "ConnectCallback connect onError-ErrorCode=" + errorCode);
            }
        });
    }

    private void startConversationList() {
        Map<String, Boolean> supportedConversation = new HashMap<>();
        supportedConversation.put(Conversation.ConversationType.PRIVATE.getName(), false);
        RongIM.getInstance().startConversationList(this, supportedConversation);
    }
}
