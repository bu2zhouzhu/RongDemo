package com.example.network;

import android.support.annotation.NonNull;

import com.example.bu2zh.model.AutoValueGsonFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.Random;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                String nonce = Integer.toString(new Random().nextInt(1000));
                String timeStamp = Long.toString(System.currentTimeMillis());
                String signature = SHA1Tool.SHA1(Constants.APP_SECRET + nonce + timeStamp);
                Request request = chain.request().newBuilder()
                        .addHeader("App-Key", Constants.APP_KEY)
                        .addHeader("Nonce", nonce)
                        .addHeader("Timestamp", timeStamp)
                        .addHeader("Signature", signature)
                        .build();
                return chain.proceed(request);
            }
        };
        httpClient.addInterceptor(interceptor);
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
