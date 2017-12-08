package com.example.bu2zh.rongdemo.rong.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.bu2zh.rongdemo.R;

import io.rong.imkit.fragment.ConversationListFragment;

public class MyConversationListFragment extends ConversationListFragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View v = view.findViewById(R.id.rc_conversation_list_empty_layout);
        if (v != null) {
            ViewGroup viewGroup = (ViewGroup) v;
            ImageView imageView = (ImageView) viewGroup.getChildAt(0);
        }
    }
}
