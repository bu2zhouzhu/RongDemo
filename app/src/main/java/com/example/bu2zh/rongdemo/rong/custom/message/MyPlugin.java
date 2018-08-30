package com.example.bu2zh.rongdemo.rong.custom.message;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;

import com.example.bu2zh.rongdemo.R;

import io.rong.imkit.RongExtension;
import io.rong.imkit.RongIM;
import io.rong.imkit.plugin.IPluginModule;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.model.Message;

public class MyPlugin implements IPluginModule {

    private boolean b;
    private String title = "MyPlugin";

    @Override
    public Drawable obtainDrawable(Context context) {
        int resId = b ? R.drawable.rc_complete_hover : R.drawable.rc_complete;
        return context.getResources().getDrawable(resId);
    }

    @Override
    public String obtainTitle(Context context) {
        return title;
    }

    @Override
    public void onClick(Fragment fragment, RongExtension rongExtension) {
        b = !b;
//        ViewPager viewPager = rongExtension.findViewById(R.id.rc_view_pager);
//        GridView gridView = (GridView) viewPager.getChildAt(0);
//        ((BaseAdapter) gridView.getAdapter()).notifyDataSetChanged();
        CustomizeMessage message = CustomizeMessage.obtain(123, "haha");
        Message message1 = Message.obtain(rongExtension.getTargetId(), rongExtension.getConversationType(), message);
        RongIM.getInstance().sendMessage(message1, null, null, (IRongCallback.ISendMessageCallback) null);
    }

    @Override
    public void onActivityResult(int i, int i1, Intent intent) {

    }

    public void setTitle(String title) {
        this.title = title;
    }
}
