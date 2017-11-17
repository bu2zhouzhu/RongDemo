package com.example.network;

import com.example.bu2zh.model.TokenResponse;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

/**
 * Created by yanlongluo on 17/11/2017.
 */

public interface RongService {

    @POST("user/getToken.json")
    @FormUrlEncoded
    Single<TokenResponse> getToken(
            @HeaderMap Map<String, String> headers,
            @Field("userId") String userId,
            @Field("name") String name,
            @Field("portraitUri") String portraitUri
    );

}
