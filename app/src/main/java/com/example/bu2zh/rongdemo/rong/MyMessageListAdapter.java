package com.example.bu2zh.rongdemo.rong;

import android.content.Context;
import android.view.View;

import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.adapter.MessageListAdapter;

/**
 * Created by luoyanlong on 2018/01/24.
 */

public class MyMessageListAdapter extends MessageListAdapter {

    public MyMessageListAdapter(Context context) {
        super(context);
    }

    @Override
    protected void bindView(View v, int position, UIMessage data) {
        super.bindView(v, position, data);
        if (data != null) {
            final MessageListAdapter.ViewHolder holder = (MessageListAdapter.ViewHolder)v.getTag();
        }
    }
}
