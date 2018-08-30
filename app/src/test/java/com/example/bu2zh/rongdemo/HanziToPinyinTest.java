package com.example.bu2zh.rongdemo;

import org.junit.Test;

import java.util.List;

/**
 * Created by luoyanlong on 2018/07/10.
 */
public class HanziToPinyinTest {

    @Test
    public void test1() {
        List<HanziToPinyin.Token> tokens = HanziToPinyin.getInstance().get("我爱");
        for (HanziToPinyin.Token token : tokens) {
            System.out.println("source: " + token.source);
            System.out.println("type: " + token.type);
            System.out.println("target: " + token.target);
        }
    }

}
