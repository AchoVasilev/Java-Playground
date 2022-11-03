package com.example.PathFinder.services.comment;

public class ErrorApiResponse {
    private String message;
    private Integer errorCode;

    public ErrorApiResponse(String message, Integer errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public ErrorApiResponse() {}

    public String getMessage() {
        return message;
    }

    public ErrorApiResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public ErrorApiResponse setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
        return this;
    }
}
