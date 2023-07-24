package com.griddynamics.internship.server.database;

import com.google.gson.JsonElement;
import com.griddynamics.internship.server.response.Response;

public interface DatabaseEngine {
    Response get(JsonElement key);
    Response set(JsonElement key, JsonElement value);
    Response delete(JsonElement key);
}
