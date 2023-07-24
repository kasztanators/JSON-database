package com.griddynamics.internship.server.database;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.griddynamics.internship.exceptions.NoSuchKeyException;
import com.griddynamics.internship.fileio.filewriter.ComplexJsonFileWriter;
import com.griddynamics.internship.fileio.filewriter.JsonFileWriter;
import com.griddynamics.internship.utils.PathManager;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static com.griddynamics.internship.utils.JsonUtils.findElement;
import static com.griddynamics.internship.utils.JsonUtils.createElement;

public class ComplexJsonDatabase implements Database {
    private final JsonObject records;
    private final JsonFileWriter writer;
    private static final Path DB_PATH = Path.of(PathManager.getPath("server/data/db.json"));


    public ComplexJsonDatabase(){
        this.writer = new ComplexJsonFileWriter(DB_PATH);
        records = loadRecords();
    }

    private JsonObject loadRecords() {
        String content;
        try {
            content = new String(Files.readAllBytes(DB_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content.isEmpty()
                ? new JsonObject()
                : new Gson().fromJson(content, JsonObject.class);
    }

    @Override
    public Optional<JsonElement> getRecord(JsonElement key) {
        if (key.isJsonPrimitive() && records.has(key.getAsString())) {
            return Optional.ofNullable(records.get(key.getAsString()));
        } else if (key.isJsonArray()) {
            return Optional.ofNullable(findElement(key.getAsJsonArray(), records));
        }
        return Optional.empty();
    }

    @Override
    public void setRecord(JsonElement key, JsonElement value) {
        if (key.isJsonPrimitive()) {
            records.add(key.getAsString(), value);
        } else if (key.isJsonArray()) {
            JsonArray keys = key.getAsJsonArray();
            String toAdd = keys.remove(keys.size() - 1).getAsString();
            createElement(keys, records).getAsJsonObject().add(toAdd, value);
        } else {
            throw new NoSuchKeyException();
        }
        writer.write(records);
    }

    @Override
    public void deleteRecord(JsonElement key) {
        if (key.isJsonPrimitive() && records.has(key.getAsString())) {
            records.remove(key.getAsString());
        } else if (key.isJsonArray()) {
            JsonArray keys = key.getAsJsonArray();
            String toRemove = keys.remove(keys.size() - 1).getAsString();
            findElement(keys, records).getAsJsonObject().remove(toRemove);
        } else {
            throw new NoSuchKeyException();
        }
        writer.write(records);
    }

}
