package com.flixsa.api.utils;

/**
 * Created by Eugene Devv on 14 Feb, 2024
 */
public class StringConstants {

    //    Error messages
    public static final String ACCOUNT_EXISTS_WITH_EMAIL_MESSAGE = "An account with this email already exists!";
    public static final String ACCOUNT_EXISTS_WITH_PHONE_MESSAGE = "An account with this phone number already exists!";
    public static final String ACCOUNT_EXISTS_MESSAGE = "Account already exists, please try again with different details";
    public static final String INVALID_EMAIL_OR_PASSWORD = "Invalid email or password";

    public static  String userWithEmailExistsMessage(String email){
        return "User with this email: "+email+", already exists";
    }
}
