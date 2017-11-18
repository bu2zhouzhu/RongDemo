package com.example.network;

import com.example.bu2zh.model.AutoValueGsonFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yanlongluo on 17/11/2017.
 */

public class ApiService {

    private static ApiService sInstance;

    private Retrofit mRetrofit;

    private ApiService() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(AutoValueGsonFactory.create())
                .create();
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.cn.ronghub.com/")
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build())
                .build();
    }

    public static ApiService getInstance() {
        if (sInstance == null) {
            synchronized (ApiService.class) {
                if (sInstance == null) {
                    sInstance = new ApiService();
                }
            }
        }
        return sInstance;
    }

    public <T> T create(Class<T> service) {
        return mRetrofit.create(service);
    }

}
