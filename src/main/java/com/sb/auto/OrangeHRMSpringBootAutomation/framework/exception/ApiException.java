package com.sb.auto.OrangeHRMSpringBootAutomation.framework.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException{

    private final int statusCode;
    private final String responseBody;

    public ApiException(int statusCode, String message, String responseBody) {
        super(message);
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }
}
