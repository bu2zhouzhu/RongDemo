package com.example.bu2zh.rongdemo.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.bu2zh.rongdemo.R;
import com.example.bu2zh.rongdemo.base.BaseActivity;

import java.util.Locale;

import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @OnClick({R.id.english, R.id.chinese})
    void onSwitchLanguageClick(View v) {
        Locale locale = Locale.getDefault();
        switch (v.getId()) {
            case R.id.english:
                locale = Locale.ENGLISH;
                break;
            case R.id.chinese:
                locale = Locale.CHINESE;
                break;
        }
        Resources resources = getApplication().getResources();
        Configuration config = resources.getConfiguration();
        config.locale = locale;
        resources.updateConfiguration(config, resources.getDisplayMetrics());
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }
}
