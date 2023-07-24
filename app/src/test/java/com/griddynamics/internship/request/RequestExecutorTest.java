package com.griddynamics.internship.request;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.griddynamics.internship.server.database.Database;
import com.griddynamics.internship.server.database.DatabaseEngine;
import com.griddynamics.internship.server.database.DatabaseEngineImpl;
import com.griddynamics.internship.server.database.SimpleJsonDatabase;
import com.griddynamics.internship.server.request.RequestExecutor;
import com.griddynamics.internship.server.response.ErrorResponse;
import com.griddynamics.internship.server.response.ExitResponse;
import com.griddynamics.internship.server.response.OkResponse;
import com.griddynamics.internship.server.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class RequestExecutorTest {
    private RequestExecutor executor;

    @BeforeEach
    public void setUp() {
        Database database = new SimpleJsonDatabase();
        DatabaseEngine engine = new DatabaseEngineImpl(database);
        executor = new RequestExecutor(engine);
    }

    @Test
    public void testRunRequest_ExitRequest() {
        JsonObject jsonRequest = new JsonObject();
        jsonRequest.addProperty("type", "exit");
        Response result = executor.executeRequest(jsonRequest);
        Assertions.assertEquals(ExitResponse.class, result.getClass());
    }

    @Test
    public void testRunRequest_ErrorRequest() {
        String message = "{\"type\":\"get\",\"key\":\"key\"}";
        JsonObject jsonRequest = JsonParser.parseString(message).getAsJsonObject();
        System.out.println(jsonRequest);

        Response result = executor.executeRequest(jsonRequest);
        Assertions.assertEquals(ErrorResponse.class, result.getClass());
    }
    @Test
    public void testRunRequest_OKRequest() {
        String message = "{\"type\":\"set\",\"key\":\"1\",\"value\":\"test\"}";
        JsonObject jsonRequest = JsonParser.parseString(message).getAsJsonObject();

        Response result = executor.executeRequest(jsonRequest);
        Assertions.assertEquals(OkResponse.class, result.getClass());
    }
}
