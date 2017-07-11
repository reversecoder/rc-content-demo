package com.reversecoder.content.helper.model;

import com.google.gson.Gson;

/**
 * Md. Rashadul Alam
 */
public abstract class WrapperBase {

    public static <T> T getResponseObject(String jsonString, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, clazz);
    }

    public static <T> String getResponseString(T object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }
}
