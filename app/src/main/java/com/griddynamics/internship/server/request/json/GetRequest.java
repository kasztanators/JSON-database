package com.griddynamics.internship.server.request.json;

import com.google.gson.JsonElement;
import lombok.Getter;

public class GetRequest implements JsonRequest{
    private static final String TYPE = "get";
    @Getter
    private JsonElement key;
}
