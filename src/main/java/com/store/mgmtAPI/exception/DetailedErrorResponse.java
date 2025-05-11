package com.store.mgmtAPI.exception;

import org.springframework.http.HttpStatus;

public class DetailedErrorResponse {
    private String apiPath;
    private HttpStatus errorCode;
    private String errorMessage;

    public DetailedErrorResponse(String apiPath, HttpStatus errorCode, String errorMessage) {
        this.apiPath = apiPath;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getApiPath() {
        return apiPath;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(HttpStatus errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "DetailedErrorResponse{" +
                "apiPath='" + apiPath + '\'' +
                ", errorCode=" + errorCode +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
