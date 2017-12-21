package com.example.bu2zh.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class SimpleResponse {

    public abstract int code();

    static TypeAdapter<SimpleResponse> typeAdapter(Gson gson) {
        return new AutoValue_SimpleResponse.GsonTypeAdapter(gson);
    }
}
