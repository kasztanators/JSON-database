package com.griddynamics.internship.utils;

import com.google.gson.GsonBuilder;

public final class GsonUtils {
    public static String prettyPrint(Object obj) {
        return new GsonBuilder()
                .setPrettyPrinting()
                .create()
                .toJson(obj);
    }
}
