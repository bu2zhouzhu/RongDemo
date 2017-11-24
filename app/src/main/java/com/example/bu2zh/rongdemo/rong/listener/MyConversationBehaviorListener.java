package com.example.bu2zh.rongdemo.rong.listener;

import android.content.Context;
import android.util.Log;
import android.view.View;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;

/**
 * 会话界面操作的监听器
 */

public class MyConversationBehaviorListener implements RongIM.ConversationBehaviorListener {

    private static final String TAG = "会话界面操作的监听器";

    /**
     * 当点击用户头像后执行
     *
     * @param context          上下文
     * @param conversationType 会话类型
     * @param userInfo         被点击的用户的信息
     * @return 如果用户自己处理了点击后的逻辑处理，则返回 true，否则返回 false，false 走融云默认处理方式
     */
    @Override
    public boolean onUserPortraitClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo) {
        Log.d(TAG, "点击用户头像");
        return false;
    }

    /**
     * 当长按用户头像后执行
     *
     * @param context          上下文
     * @param conversationType 会话类型
     * @param userInfo         被点击的用户的信息
     * @return 如果用户自己处理了点击后的逻辑处理，则返回 true，否则返回 false，false 走融云默认处理方式
     */
    @Override
    public boolean onUserPortraitLongClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo) {
        Log.d(TAG, "长按用户头像");
        return false;
    }

    /**
     * 当点击消息时执行
     *
     * @param context 上下文
     * @param view    触发点击的 View
     * @param message 被点击的消息的实体信息
     * @return 如果用户自己处理了点击后的逻辑处理，则返回 true， 否则返回 false, false 走融云默认处理方式
     */
    @Override
    public boolean onMessageClick(Context context, View view, Message message) {
        Log.d(TAG, "点击消息");
        return false;
    }

    /**
     * 当点击链接消息时执行
     *
     * @param context 上下文
     * @param s       被点击的链接
     * @return 如果用户自己处理了点击后的逻辑处理，则返回 true， 否则返回 false, false 走融云默认处理方式
     */
    @Override
    public boolean onMessageLinkClick(Context context, String s) {
        Log.d(TAG, "点击链接消息");
        return false;
    }

    /**
     * 当长按消息时执行
     *
     * @param context 上下文
     * @param view    触发点击的 View
     * @param message 被长按的消息的实体信息
     * @return 如果用户自己处理了长按后的逻辑处理，则返回 true，否则返回 false，false 走融云默认处理方式
     */
    @Override
    public boolean onMessageLongClick(Context context, View view, Message message) {
        Log.d(TAG, "长按消息");
        return false;
    }
}
