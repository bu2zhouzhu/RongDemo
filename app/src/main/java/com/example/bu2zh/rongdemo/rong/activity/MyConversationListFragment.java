package com.example.bu2zh.rongdemo.rong.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.bu2zh.rongdemo.R;

import io.rong.imkit.fragment.ConversationListFragment;

public class MyConversationListFragment extends ConversationListFragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView = view.findViewById(R.id.rc_list);
    }
}
