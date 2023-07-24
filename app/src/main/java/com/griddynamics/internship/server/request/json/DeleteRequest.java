package com.griddynamics.internship.server.request.json;

import com.google.gson.JsonElement;
import lombok.Getter;

public class DeleteRequest implements JsonRequest {
    private static final String TYPE = "delete";
    @Getter
    private JsonElement key;
}
