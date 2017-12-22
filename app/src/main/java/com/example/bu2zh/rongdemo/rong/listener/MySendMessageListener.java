package com.example.bu2zh.rongdemo.rong.listener;

import android.content.Context;
import android.util.Log;

import com.example.bu2zh.rongdemo.sp.MessageSp;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Message;

/**
 * 获取自己发出的消息监听器
 */

public class MySendMessageListener implements RongIM.OnSendMessageListener {

    private static final String TAG = "发送消息监听器";
    private Context mContext;

    public MySendMessageListener(Context context) {
        this.mContext = context;
    }

    /**
     * 消息发送前监听器处理接口（是否发送成功可以从SentStatus属性获取） 可以通过这个方法，过滤，修改发送出的消息。
     * @param message 发送的消息实例
     * @return 处理后的消息实例，注意：可以通过 return 的返回值，过滤消息 当 return null 时，该消息不发送，界面也无显示 也可以更改 message 内的消息内容，发送出的消息，就是更改后的。
     */
    @Override
    public Message onSend(Message message) {
        Log.d(TAG, "OnSendMessageListener.onSend");
        return message;
    }

    /**
     * 消息发送后回调接口
     * @param message 消息实例
     * @param sentMessageErrorCode 发送消息失败的状态码，消息发送成功 SentMessageErrorCode 为 null
     * @return 不知道
     */
    @Override
    public boolean onSent(Message message, RongIM.SentMessageErrorCode sentMessageErrorCode) {
        new MessageSp(mContext).setId(message.getMessageId());
        return false;
    }
}
