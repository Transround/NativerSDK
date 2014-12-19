package com.transround.nativeradmin.model;

/**
 * Created by szeibert on 2014.12.10..
 */
public class JsonErrorResponse {
    private String errorType;
    private String errorMessage;

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
