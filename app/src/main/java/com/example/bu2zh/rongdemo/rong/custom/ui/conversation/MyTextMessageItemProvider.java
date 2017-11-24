package com.example.bu2zh.rongdemo.rong.custom.ui.conversation;

import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.widget.provider.TextMessageItemProvider;
import io.rong.message.TextMessage;

@ProviderTag(messageContent = TextMessage.class, centerInHorizontal = true)
public class MyTextMessageItemProvider extends TextMessageItemProvider {
}
