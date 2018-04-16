package com.example.bu2zh.rongdemo.rong.custom;

import android.content.Context;
import android.view.View;

import io.rong.imkit.RongContext;
import io.rong.imkit.model.ConversationKey;
import io.rong.imkit.model.UIConversation;
import io.rong.imkit.widget.adapter.ConversationListAdapter;
import io.rong.imlib.model.Conversation;

/**
 * Created by luoyanlong on 2018/01/26.
 */

public class ConversationListAdapterEx extends ConversationListAdapter {
    public ConversationListAdapterEx(Context context) {
        super(context);
    }

    @Override
    protected void bindView(View v, int position, UIConversation data) {
        if (data != null) {
            ConversationKey key = ConversationKey.obtain(data.getConversationTargetId(), data.getConversationType());
            Conversation.ConversationNotificationStatus status = RongContext.getInstance().getConversationNotifyStatusFromCache(key);
            if (status == Conversation.ConversationNotificationStatus.DO_NOT_DISTURB) {
                data.setUnreadType(UIConversation.UnreadRemindType.REMIND_ONLY);
            }
        }
        super.bindView(v, position, data);
    }
}
