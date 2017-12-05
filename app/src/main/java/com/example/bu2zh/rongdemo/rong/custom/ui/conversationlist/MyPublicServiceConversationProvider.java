package com.example.bu2zh.rongdemo.rong.custom.ui.conversationlist;

import android.graphics.Color;
import android.view.View;

import io.rong.imkit.model.ConversationProviderTag;
import io.rong.imkit.model.UIConversation;
import io.rong.imkit.widget.provider.PublicServiceConversationProvider;

/**
 * Created by yanlongluo on 04/12/2017.
 */
@ConversationProviderTag(conversationType = "public_service", portraitPosition = 1)
public class MyPublicServiceConversationProvider extends PublicServiceConversationProvider {

    @Override
    public void bindView(View view, int position, UIConversation data) {
        super.bindView(view, position, data);
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.title.setTextColor(Color.BLUE);
    }

}
