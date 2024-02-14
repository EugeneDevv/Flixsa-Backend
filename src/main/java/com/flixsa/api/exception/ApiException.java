package com.flixsa.api.exception;

/**
 * Created by Eugene Devv on 14 Feb, 2024
 */
public class ApiException extends RuntimeException{
    public ApiException(String message) {
        super(message);
    }
}
