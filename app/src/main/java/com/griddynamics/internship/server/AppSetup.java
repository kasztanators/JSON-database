package com.griddynamics.internship.server;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.griddynamics.internship.server.database.*;
import com.griddynamics.internship.server.request.RequestExecutor;
import com.griddynamics.internship.server.response.ExitResponse;
import com.griddynamics.internship.server.response.Response;
import lombok.Getter;
@Getter
public class AppSetup {
    private volatile static AppSetup instance;
    private final RequestExecutor requestExecutor;

    private AppSetup() {
        Database database = new ComplexJsonDatabase();
        DatabaseEngine engine = new DatabaseEngineImpl(database);
        this.requestExecutor = new RequestExecutor(engine);
    }

    public static AppSetup getInstance() {
        if (instance == null) {
            synchronized (AppSetup.class) {
                if (instance == null) {
                    instance = new AppSetup();
                }
            }
        }
        return instance;
    }

    public Response run(String message, Interrupter server) {
        JsonObject json = JsonParser.parseString(message).getAsJsonObject();
        Response response = requestExecutor.executeRequest(json);
        if (response instanceof ExitResponse) {
            server.interrupt();
        }
        return response;
    }
}
