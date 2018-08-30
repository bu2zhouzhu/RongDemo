package com.example.bu2zh.rongdemo.rong.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.bu2zh.rongdemo.R;

import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imkit.model.Event;
import io.rong.imkit.widget.adapter.MessageListAdapter;

public class MyConversationFragment extends ConversationFragment {

    private ListView mList;
    private MessageListAdapter mListAdapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mList = view.findViewById(R.id.rc_list);
    }

    @Override
    public MessageListAdapter onResolveAdapter(Context context) {
        mListAdapter = super.onResolveAdapter(context);
        return mListAdapter;
    }

    public void onEventMainThread(Event.MessageSentStatusEvent event) {
        int position = mListAdapter.findPosition(event.getMessageId());
        if (position >= 0) {
            mListAdapter.getItem(position).setSentStatus(event.getSentStatus());
            mListAdapter.getView(position, getListViewChildAt(position), mList);
        }
    }

    private View getListViewChildAt(int adapterIndex) {
        int header = this.mList.getHeaderViewsCount();
        int first = this.mList.getFirstVisiblePosition();
        return this.mList.getChildAt(adapterIndex + header - first);
    }

}
