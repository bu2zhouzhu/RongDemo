package com.example.bu2zh.rongdemo.rong.listener;

import android.util.Log;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;
import io.rong.message.ImageMessage;
import io.rong.message.RichContentMessage;
import io.rong.message.TextMessage;
import io.rong.message.VoiceMessage;

/**
 * 获取自己发出的消息监听器
 */

public class MySendMessageListener implements RongIM.OnSendMessageListener {

    private static final String TAG = "发送消息监听器";

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
        if(message.getSentStatus()== Message.SentStatus.FAILED){
            if(sentMessageErrorCode== RongIM.SentMessageErrorCode.NOT_IN_CHATROOM){
                Log.d(TAG, "不在聊天室");
            }else if(sentMessageErrorCode== RongIM.SentMessageErrorCode.NOT_IN_DISCUSSION){
                Log.d(TAG, "不在讨论组");
            }else if(sentMessageErrorCode== RongIM.SentMessageErrorCode.NOT_IN_GROUP){
                Log.d(TAG, "不在群组");
            }else if(sentMessageErrorCode== RongIM.SentMessageErrorCode.REJECTED_BY_BLACKLIST){
                Log.d(TAG, "你在他的黑名单中");
            }
        }

        MessageContent messageContent = message.getContent();

        if (messageContent instanceof TextMessage) {//文本消息
            TextMessage textMessage = (TextMessage) messageContent;
            Log.d(TAG, "onSent-TextMessage:" + textMessage.getContent());
        } else if (messageContent instanceof ImageMessage) {//图片消息
            ImageMessage imageMessage = (ImageMessage) messageContent;
            Log.d(TAG, "onSent-ImageMessage:" + imageMessage.getRemoteUri());
        } else if (messageContent instanceof VoiceMessage) {//语音消息
            VoiceMessage voiceMessage = (VoiceMessage) messageContent;
            Log.d(TAG, "onSent-voiceMessage:" + voiceMessage.getUri().toString());
        } else if (messageContent instanceof RichContentMessage) {//图文消息
            RichContentMessage richContentMessage = (RichContentMessage) messageContent;
            Log.d(TAG, "onSent-RichContentMessage:" + richContentMessage.getContent());
        } else {
            Log.d(TAG, "onSent-其他消息，自己来判断处理");
        }
        return false;
    }
}
