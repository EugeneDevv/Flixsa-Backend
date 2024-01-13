package com.smartspotsolutions.flixsa.exception;

/**
 * Created by Eugene Devv on 20 Dec, 2023
 */
public class ApiException extends RuntimeException{
    public ApiException(String message) {
        super(message);
    }
}
