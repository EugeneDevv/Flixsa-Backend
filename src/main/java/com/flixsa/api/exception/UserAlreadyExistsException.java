package com.flixsa.api.exception;

/**
 * Created by Eugene Devv on 13 Feb, 2024
 */
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
