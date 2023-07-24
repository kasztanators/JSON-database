package com.griddynamics.internship.server.request;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.griddynamics.internship.exceptions.NoSuchRequestException;
import com.griddynamics.internship.server.database.DatabaseEngine;
import com.griddynamics.internship.server.request.json.DeleteRequest;
import com.griddynamics.internship.server.request.json.GetRequest;
import com.griddynamics.internship.server.request.json.SetRequest;
import com.griddynamics.internship.server.response.ExitResponse;
import com.griddynamics.internship.server.response.Response;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RequestExecutor {
    private final DatabaseEngine engine;
    private final Gson gson;
    private final ReadWriteLock readWriteLock;

    public RequestExecutor(DatabaseEngine engine) {
        this.engine = engine;
        this.gson = new Gson();
        readWriteLock = new ReentrantReadWriteLock();
    }

    public Response executeRequest(JsonObject request) {
        String type = request.get("type").getAsString();
        switch (type) {
            case "exit":
                return new ExitResponse();
            case "get":
                return executeGetRequest(request);
            case "set":
                return executeSetRequest(request);
            case "delete":
                return executeDeleteRequest(request);
            default:
                throw new NoSuchRequestException();
        }
    }

    private Response executeGetRequest(JsonObject request) {
        readWriteLock.readLock().lock();
        try {
            GetRequest getRequest = gson.fromJson(request, GetRequest.class);
            return engine.get(getRequest.getKey());
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    private Response executeSetRequest(JsonObject request) {
        readWriteLock.writeLock().lock();
        try {
            SetRequest setRequest = gson.fromJson(request, SetRequest.class);
            return engine.set(setRequest.getKey(), setRequest.getValue());
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    private Response executeDeleteRequest(JsonObject request) {
        readWriteLock.writeLock().lock();
        try {
            DeleteRequest deleteRequest = gson.fromJson(request, DeleteRequest.class);
            return engine.delete(deleteRequest.getKey());
        } finally{
            readWriteLock.writeLock().unlock();
        }
    }
}
