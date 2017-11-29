package com.example.bu2zh.rongdemo.rong.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.example.bu2zh.rongdemo.R;
import com.example.bu2zh.rongdemo.rong.custom.message.MyExtensionModule;

import java.util.List;

import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.IExtensionModule;
import io.rong.imkit.RongExtensionManager;
import io.rong.imkit.fragment.ConversationFragment;

/**
 * 配置会话界面
 */

public class ConversationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ("/conversation/discussion".equals(getIntent().getData().getPath())) {
            List<IExtensionModule> moduleList = RongExtensionManager.getInstance().getExtensionModules();
            RongExtensionManager.getInstance().unregisterExtensionModule(moduleList.get(0));
            RongExtensionManager.getInstance().registerExtensionModule(new DefaultExtensionModule());
        } else {
            List<IExtensionModule> moduleList = RongExtensionManager.getInstance().getExtensionModules();
            RongExtensionManager.getInstance().unregisterExtensionModule(moduleList.get(0));
            RongExtensionManager.getInstance().registerExtensionModule(new MyExtensionModule());
        }
        setContentView(R.layout.activity_conversation);
    }

    @Override
    public void onBackPressed() {
        ConversationFragment fragment = (ConversationFragment) getSupportFragmentManager().findFragmentById(R.id.conversation);
        if(!fragment.onBackPressed()) {
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}
