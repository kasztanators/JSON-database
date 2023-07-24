package com.griddynamics.internship.server.response;


import com.google.gson.JsonElement;
import lombok.Getter;

@Getter
public class DbValueResponse implements Response{
    private final String response;
    private final JsonElement value;
    public DbValueResponse(JsonElement value) {
        this.response = "OK";
        this.value =  value;
    }
}
