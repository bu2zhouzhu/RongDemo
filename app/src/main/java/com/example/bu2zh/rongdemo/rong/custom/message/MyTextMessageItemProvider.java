package com.example.bu2zh.rongdemo.rong.custom.message;

import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.widget.provider.TextMessageItemProvider;
import io.rong.message.TextMessage;

/**
 * Created by yanlongluo on 04/12/2017.
 */
@ProviderTag(messageContent = TextMessage.class, showReadState = true, showPortrait = true)
public class MyTextMessageItemProvider extends TextMessageItemProvider {

}
