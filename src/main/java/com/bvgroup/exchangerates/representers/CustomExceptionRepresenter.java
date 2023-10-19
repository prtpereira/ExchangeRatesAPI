package com.bvgroup.exchangerates.representers;

public class CustomExceptionRepresenter {
    public String type;
    public String message;
    public String exception = null;

    public CustomExceptionRepresenter(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public CustomExceptionRepresenter(String type, String message, String exception) {
        this.type = type;
        this.message = message;
        this.exception = exception;
    }
}
