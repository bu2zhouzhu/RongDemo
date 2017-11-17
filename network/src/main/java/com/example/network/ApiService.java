package com.example.network;

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
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.cn.ronghub.com/")
                .addConverterFactory(GsonConverterFactory.create())
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
