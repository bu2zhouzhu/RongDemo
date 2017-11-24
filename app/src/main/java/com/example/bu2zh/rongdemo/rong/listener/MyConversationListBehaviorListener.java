package com.example.bu2zh.rongdemo.rong.listener;

import android.content.Context;
import android.util.Log;
import android.view.View;

import io.rong.imkit.RongIM;
import io.rong.imkit.model.UIConversation;
import io.rong.imlib.model.Conversation;

/**
 * 会话列表操作监听器
 */

public class MyConversationListBehaviorListener implements RongIM.ConversationListBehaviorListener {

    private static final String TAG = "会话列表操作";

    /**
     * 当点击会话头像后执行
     *
     * @param context          上下文
     * @param conversationType 会话类型
     * @param s                被点击的用户id
     * @return 如果用户自己处理了点击后的逻辑处理，则返回 true，否则返回 false，false 走融云默认处理方式
     */
    @Override
    public boolean onConversationPortraitClick(Context context, Conversation.ConversationType conversationType, String s) {
        Log.d(TAG, "点击会话头像");
        return false;
    }

    /**
     * 当长按会话头像后执行
     *
     * @param context          上下文
     * @param conversationType 会话类型
     * @param s                被点击的用户id
     * @return 如果用户自己处理了点击后的逻辑处理，则返回 true，否则返回 false，false 走融云默认处理方式
     */
    @Override
    public boolean onConversationPortraitLongClick(Context context, Conversation.ConversationType conversationType, String s) {
        Log.d(TAG, "长按会话头像");
        return false;
    }

    /**
     * 长按会话列表中的 item 时执行
     *
     * @param context        上下文
     * @param view           触发点击的 View
     * @param uiConversation 长按时的会话条目
     * @return 如果用户自己处理了长按会话后的逻辑处理，则返回 true， 否则返回 false，false 走融云默认处理方式
     */
    @Override
    public boolean onConversationLongClick(Context context, View view, UIConversation uiConversation) {
        Log.d(TAG, "长按会话列表");
        return false;
    }

    /**
     * 点击会话列表中的 item 时执行
     *
     * @param context        上下文
     * @param view           触发点击的 View
     * @param uiConversation 会话条目
     * @return 如果用户自己处理了点击会话后的逻辑处理，则返回 true， 否则返回 false，false 走融云默认处理方式
     */
    @Override
    public boolean onConversationClick(Context context, View view, UIConversation uiConversation) {
        Log.d(TAG, "点击会话列表");
        return false;
    }
}
