package com.example.bu2zh.rongdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.bu2zh.model.TokenResponse;
import com.example.network.ApiService;
import com.example.network.RongService;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";

    @OnClick(R.id.btn1)
    void onClick() {
        Map<String, Boolean> supportedConversation = new HashMap<>();
        supportedConversation.put(Conversation.ConversationType.PRIVATE.getName(), false);
        RongIM.getInstance().startConversationList(this, supportedConversation);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        RongService service = ApiService.getInstance().create(RongService.class);
        Map<String, String> headers = new HashMap<>();
        headers.put("App-Key", "");
        headers.put("Nonce", "");
        headers.put("Timestamp", "");
        headers.put("Signature", "");

        service.getToken(headers, "5", "test5", "ha")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<TokenResponse>() {
                            @Override
                            public void accept(TokenResponse tokenResponse) throws Exception {
                                Log.d(TAG, "token: " + tokenResponse.getToken());
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                throwable.printStackTrace();
                            }
                        }
                );

        String token = "+VH3GeUAoKNH0bdkp5eSety/0uRxRwM05z0kWb35eTEvcy9OXHQYPoCxL1OzjM75JmwN5CnhcM0S5QnFgU99Nw==";
        String token2 = "MXustJwYwa5fwIWIvqOU6pAQhuZbGq8TRJMXwUzMbbiTPxC1cSuHyjWkTAIqo9gqG8Zf3AKu8lRkJcTGSalEzQ==";
//        RongIM.connect(token2, new RongIMClient.ConnectCallback() {
//            @Override
//            public void onTokenIncorrect() {
//                Log.d(TAG, "ConnectCallback connect onTokenIncorrect");
//            }
//
//            @Override
//            public void onSuccess(String s) {
//                Log.d(TAG, "ConnectCallback connect onSuccess " + s);
//            }
//
//            @Override
//            public void onError(RongIMClient.ErrorCode errorCode) {
//                Log.d(TAG, "ConnectCallback connect onError-ErrorCode=" + errorCode);
//            }
//        });
    }
}
