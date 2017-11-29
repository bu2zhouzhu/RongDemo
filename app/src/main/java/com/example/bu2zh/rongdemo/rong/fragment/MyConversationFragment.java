package com.example.bu2zh.rongdemo.rong.fragment;

import android.view.View;
import android.view.ViewGroup;

import io.rong.imkit.fragment.ConversationFragment;

public class MyConversationFragment extends ConversationFragment {

    private Callback mCb;

    @Override
    public void onPluginToggleClick(View v, ViewGroup extensionBoard) {
        if (mCb != null) {
            mCb.onToggleClick();
        }
    }

    public interface Callback {
        void onToggleClick();
    }

    public void setCallback(Callback cb) {
        this.mCb = cb;
    }
}
