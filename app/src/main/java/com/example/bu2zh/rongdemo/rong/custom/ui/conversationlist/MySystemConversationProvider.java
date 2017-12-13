package com.example.bu2zh.rongdemo.rong.custom.ui.conversationlist;

import android.graphics.Color;
import android.net.Uri;
import android.view.View;

import io.rong.imkit.model.ConversationProviderTag;
import io.rong.imkit.model.UIConversation;
import io.rong.imkit.widget.provider.SystemConversationProvider;

@ConversationProviderTag(conversationType = "system")
public class MySystemConversationProvider extends SystemConversationProvider {

    @Override
    public void bindView(View view, int position, UIConversation data) {
        super.bindView(view, position, data);
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.title.setTextColor(Color.RED);
    }

    @Override
    public Uri getPortraitUri(String userId) {
        return Uri.parse("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2913668656,2233921135&fm=200&gp=0.jpg");
    }
}
