package com.example.bu2zh.rongdemo.rong.custom.ui.conversationlist;

import android.graphics.Color;
import android.view.View;

import io.rong.imkit.model.ConversationProviderTag;
import io.rong.imkit.model.UIConversation;
import io.rong.imkit.widget.provider.PrivateConversationProvider;

@ConversationProviderTag(conversationType = "private", portraitPosition = 1)
public class MyPrivateConversationProvider extends PrivateConversationProvider {

    @Override
    public void bindView(View view, int position, UIConversation data) {
        super.bindView(view, position, data);
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.title.setTextColor(Color.RED);
    }

}
