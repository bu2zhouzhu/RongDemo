package com.example.bu2zh.rongdemo.rong.activity;

import android.content.Context;

import com.example.bu2zh.rongdemo.rong.custom.MyConversationListAdapter;

import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imkit.widget.adapter.ConversationListAdapter;

public class MyConversationListFragment extends ConversationListFragment {

    @Override
    public ConversationListAdapter onResolveAdapter(Context context) {
        return new MyConversationListAdapter(context);
    }
}
