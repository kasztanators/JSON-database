package com.griddynamics.internship.server.database;

import com.google.gson.JsonElement;

import java.util.Optional;

public interface Database {
    Optional<JsonElement> getRecord(JsonElement key);
    void setRecord(JsonElement key, JsonElement data);
    void deleteRecord(JsonElement key);
}
