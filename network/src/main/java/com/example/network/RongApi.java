package com.example.network;

import com.example.bu2zh.model.SimpleResponse;
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

public interface RongApi {

    @POST("user/getToken.json")
    @FormUrlEncoded
    Single<TokenResponse> getToken(
            @Field("userId") String userId,
            @Field("name") String userName,
            @Field("portraitUri") String portraitUri
    );

    @POST("group/create.json")
    @FormUrlEncoded
    Single<SimpleResponse> createGroup(
            @HeaderMap Map<String, String> headers,
            @Field("userId") String userId,
            @Field("name") String userName,
            @Field("portraitUri") String portraitUri
    );

    @POST("group/join.json")
    @FormUrlEncoded
    Single<SimpleResponse> joinGroup(
            @Field("userId") String userId,
            @Field("groupId") String groupId,
            @Field("groupName") String groupName
    );

}
