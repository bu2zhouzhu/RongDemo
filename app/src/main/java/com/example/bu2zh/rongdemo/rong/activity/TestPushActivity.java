package com.example.bu2zh.rongdemo.rong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.bu2zh.rongdemo.base.BaseActivity;

import io.rong.push.RongPushClient;

public class TestPushActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        getPushMessage(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getPushMessage(intent);
    }

    /**
     * Android push 消息
     */
    private void getPushMessage(Intent intent) {

        if (intent != null && intent.getData() != null && intent.getData().getScheme().equals("rong")) {

            //该条推送消息的内容。
            String content = intent.getData().getQueryParameter("pushContent");
            //标识该推送消息的唯一 Id。
            String id = intent.getData().getQueryParameter("pushId");
            //用户自定义参数 json 格式，解析后用户可根据自己定义的 Key 、Value 值进行业务处理。
            String extra = intent.getData().getQueryParameter("extra");
            //统计通知栏点击事件.
            RongPushClient.recordNotificationEvent(id);
            Log.d("TestPushActivity", "--content:" + content + "--id:" + id + "---extra:" + extra);
        }
    }
}
