package com.example.bu2zh.rongdemo.rong.custom;

import android.content.Context;
import android.view.View;

import io.rong.imkit.model.UIConversation;
import io.rong.imkit.widget.adapter.ConversationListAdapter;
import io.rong.imlib.model.Conversation;

/**
 * Created by luoyanlong on 2018/01/26.
 */

public class MyConversationListAdapter extends ConversationListAdapter {

    public MyConversationListAdapter(Context context) {
        super(context);
    }

    @Override
    protected void bindView(View v, int position, UIConversation data) {
        super.bindView(v, position, data);
        if (data != null) {
            ViewHolder holder = (ViewHolder) v.getTag();
            if (data.getConversationGatherState()
                    && data.getConversationType() == Conversation.ConversationType.SYSTEM)
            holder.leftImageView.setAvatar();
        }
    }
}
