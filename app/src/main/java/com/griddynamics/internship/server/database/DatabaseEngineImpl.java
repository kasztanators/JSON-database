package com.griddynamics.internship.server.database;

import com.google.gson.JsonElement;
import com.griddynamics.internship.exceptions.NoSuchKeyException;
import com.griddynamics.internship.server.response.DbValueResponse;
import com.griddynamics.internship.server.response.ErrorResponse;
import com.griddynamics.internship.server.response.Response;
import com.griddynamics.internship.server.response.OkResponse;

import java.util.Optional;

public class DatabaseEngineImpl implements DatabaseEngine {
    private final Database database;

    public DatabaseEngineImpl(Database database) {
        this.database = database;
    }

    @Override
    public Response get(JsonElement key) {
        try {
            Optional<JsonElement> record = database.getRecord(key);
            return new DbValueResponse(record.orElseThrow(NoSuchKeyException::new));
        } catch (NoSuchKeyException e) {
            return new ErrorResponse(e.getMessage());
        }
    }

    @Override
    public Response set(JsonElement key, JsonElement data) {
        try{
            database.setRecord(key, data);
            return new OkResponse();
        }
        catch (IndexOutOfBoundsException e){
            return new ErrorResponse(e.getMessage());
        }
    }

    @Override
    public Response delete(JsonElement key) {
        try{
            database.deleteRecord(key);
            return new OkResponse();
        }
        catch (NoSuchKeyException e) {
            return new ErrorResponse(e.getMessage());
        }
    }
}
