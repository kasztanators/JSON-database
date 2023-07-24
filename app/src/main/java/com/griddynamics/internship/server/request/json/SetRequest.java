package com.griddynamics.internship.server.request.json;

import com.google.gson.JsonElement;
import lombok.Getter;

@Getter
public class SetRequest implements JsonRequest {
    private static final String TYPE = "set";
    private JsonElement key;
    private JsonElement value;
}
