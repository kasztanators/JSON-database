package com.griddynamics.internship.exceptions;

public class NoSuchRequestException extends RuntimeException{
    public NoSuchRequestException() {
        super("No such request");
    }
}
