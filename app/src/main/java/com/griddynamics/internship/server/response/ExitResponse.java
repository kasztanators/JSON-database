package com.griddynamics.internship.server.response;

import lombok.Getter;

@Getter

public class ExitResponse implements Response{
    private final String response = "OK";
}
