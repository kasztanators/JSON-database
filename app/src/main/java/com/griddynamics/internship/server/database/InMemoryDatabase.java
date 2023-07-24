package com.griddynamics.internship.server.database;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.griddynamics.internship.exceptions.NoSuchKeyException;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Optional;
@Setter
@Getter
public class InMemoryDatabase implements Database{
    private Map<String, String> records;

    public InMemoryDatabase(Map<String, String> records) {
        this.records = records;
    }

    public void setRecord(JsonElement key, JsonElement value) {

        records.put(key.getAsString(), value.getAsString());
    }

    public void deleteRecord(JsonElement key) {
        String keyString = key.getAsString();
        if(records.containsKey(keyString)) {
            records.remove(keyString);
        }
        else{
            throw new NoSuchKeyException();
        }
    }

    public Optional<JsonElement> getRecord(JsonElement key) {
        Optional<String> result = Optional.ofNullable(records.get(key.getAsString()))
                .filter(record -> !record.isEmpty());
        return result.map(JsonParser::parseString);
    }
}
