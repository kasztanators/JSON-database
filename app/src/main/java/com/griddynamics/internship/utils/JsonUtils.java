package com.griddynamics.internship.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.griddynamics.internship.exceptions.NoSuchKeyException;

public class JsonUtils {
     public static JsonElement findElement(JsonArray keys, JsonObject records) {
        JsonElement currElement = records;
         for (JsonElement key: keys) {
             if (!key.isJsonPrimitive() || !currElement.getAsJsonObject().has(key.getAsString())) {
                 throw new NoSuchKeyException();
             }
             currElement = currElement.getAsJsonObject().get(key.getAsString());
         }
        return currElement;
    }

    // Creates or retrieves nested JSON elements using the provided keys.
    public static JsonElement createElement(JsonArray keys, JsonObject records) {
        JsonElement currElement = records;

        for (JsonElement key: keys) {
            if (!currElement.getAsJsonObject().has(key.getAsString())) {
                currElement.getAsJsonObject().add(key.getAsString(), new JsonObject());
            }
            currElement = currElement.getAsJsonObject().get(key.getAsString());
        }

        return currElement;
    }
}
