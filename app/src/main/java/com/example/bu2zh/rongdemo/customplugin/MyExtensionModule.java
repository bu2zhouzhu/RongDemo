package com.example.bu2zh.rongdemo.customplugin;

import java.util.List;

import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.plugin.IPluginModule;
import io.rong.imlib.model.Conversation;

/**
 * Created by yanlongluo on 23/11/2017.
 */

public class MyExtensionModule extends DefaultExtensionModule {
    private MyPlugin myPlugin;

    public MyExtensionModule() {
        myPlugin = new MyPlugin();
    }

    @Override
    public List<IPluginModule> getPluginModules(Conversation.ConversationType conversationType) {
        List<IPluginModule> pluginModules =  super.getPluginModules(conversationType);
        pluginModules.add(myPlugin);
        return pluginModules;
    }
}
