package com.transround.nativeradmin.model;

/**
 * Created by szeibert on 2014.11.27..
 */
public class ServiceResult {
    private String result;
    private String errorMessage;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return this.result.equalsIgnoreCase("ok");
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
