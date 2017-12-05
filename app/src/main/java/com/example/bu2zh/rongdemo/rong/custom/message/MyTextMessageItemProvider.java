package com.example.bu2zh.rongdemo.rong.custom.message;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.provider.TextMessageItemProvider;
import io.rong.imlib.model.Message;
import io.rong.message.TextMessage;

/**
 * Created by yanlongluo on 04/12/2017.
 */
@ProviderTag(messageContent = TextMessage.class, showReadState = true)
public class MyTextMessageItemProvider extends TextMessageItemProvider {

    @Override
    public void bindView(View v, int position, TextMessage content, UIMessage data) {
        super.bindView(v, position, content, data);
        TextView textView = (TextView) v;
        if (data.getMessageDirection() == Message.MessageDirection.SEND) {
            textView.setTextColor(Color.WHITE);
        } else {
            textView.setTextColor(Color.BLACK);
        }
    }
}
