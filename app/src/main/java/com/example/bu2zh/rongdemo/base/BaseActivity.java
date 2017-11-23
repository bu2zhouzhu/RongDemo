package com.example.bu2zh.rongdemo.base;

import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by yanlongluo on 23/11/2017.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }
}
