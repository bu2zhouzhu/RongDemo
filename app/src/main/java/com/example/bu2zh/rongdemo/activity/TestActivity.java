package com.example.bu2zh.rongdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.bu2zh.rongdemo.R;
import com.example.bu2zh.rongdemo.base.BaseActivity;

import butterknife.OnClick;

public class TestActivity extends BaseActivity {

    @OnClick(R.id.btn)
    void onClick() {
        throw new RuntimeException();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }
}
