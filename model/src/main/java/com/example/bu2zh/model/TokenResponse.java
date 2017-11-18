package com.example.bu2zh.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class TokenResponse {

    public abstract int code();

    public abstract String userId();

    public abstract String token();

    static TypeAdapter<TokenResponse> typeAdapter(Gson gson) {
        return new AutoValue_TokenResponse.GsonTypeAdapter(gson);
    }
}
