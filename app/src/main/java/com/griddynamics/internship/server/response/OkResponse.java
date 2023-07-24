package com.griddynamics.internship.server.response;

import lombok.Getter;

@Getter
public class OkResponse implements Response{
    private final String response = "OK";
}
