package com.hyeyeon.Postpost.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final int httpStatusCode;
    
    private final String message;

    public BusinessException(int httpStatusCode, String message) {
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }
}
