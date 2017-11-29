package com.example.bu2zh.rongdemo.rong.custom.message;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.example.bu2zh.rongdemo.R;

import java.lang.reflect.Field;
import java.util.List;

import io.rong.imkit.RongExtension;
import io.rong.imkit.RongIM;
import io.rong.imkit.plugin.IPluginModule;
import io.rong.imkit.plugin.PluginAdapter;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;

public class MyPlugin implements IPluginModule {

    private String title = "MyPlugin";

    private IRongCallback.ISendMessageCallback callback = new IRongCallback.ISendMessageCallback() {
        @Override
        public void onAttached(Message message) {

        }

        @Override
        public void onSuccess(Message message) {

        }

        @Override
        public void onError(Message message, RongIMClient.ErrorCode errorCode) {

        }
    };

    @Override
    public Drawable obtainDrawable(Context context) {
        return context.getResources().getDrawable(R.mipmap.ic_launcher);
    }

    @Override
    public String obtainTitle(Context context) {
        return title;
    }

    @Override
    public void onClick(Fragment fragment, RongExtension rongExtension) {
        ViewGroup vg = (ViewGroup) rongExtension.getChildAt(1);
        vg = (ViewGroup) vg.getChildAt(0);
        vg = (ViewPager) vg.getChildAt(0);
        GridView gridView = (GridView) vg.getChildAt(0);
        BaseAdapter adapter = (BaseAdapter) gridView.getAdapter();

        try {
            Field f = RongExtension.class.getDeclaredField("mPluginAdapter");
            f.setAccessible(true);
            PluginAdapter pluginAdapter = (PluginAdapter) f.get(rongExtension);
            f = pluginAdapter.getClass().getDeclaredField("mPluginModules");
            f.setAccessible(true);
            List<IPluginModule> pluginModules = (List<IPluginModule>) f.get(pluginAdapter);
            MyPlugin myPlugin = (MyPlugin) pluginModules.get(pluginModules.size() - 1);
            myPlugin.setTitle("change");
            adapter.notifyDataSetChanged();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        CustomizeMessage content = new CustomizeMessage("CustomMessage");
        String targetId = rongExtension.getTargetId();
        Message message = Message.obtain(targetId, Conversation.ConversationType.PRIVATE, content);
        RongIM.getInstance().sendMessage(message, null, null, callback);
    }

    @Override
    public void onActivityResult(int i, int i1, Intent intent) {

    }

    public void setTitle(String title) {
        this.title = title;
    }
}
