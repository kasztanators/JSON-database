package com.griddynamics.internship.server.response;

import lombok.Getter;

@Getter
public class ErrorResponse implements Response{
    private final String response;
    private final String reason;

    public ErrorResponse(String reason) {
        this.response = "ERROR";
        this.reason = reason;
    }
}
