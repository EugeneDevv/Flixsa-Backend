package com.smartspotsolutions.flixsa.query;

/**
 * Created by Eugene Devv on 20 Dec, 2023
 */
public class UserQuery  {

    public static final String  INSERT_USER_QUERY = "INSERT INTO Users (first_name, last_name, email, password) VALUES (:firstName, :lastName, :email, :password)";
    public static final String COUNT_USER_EMAIL_QUERY = "SELECT COUNT(*) FROM Users WHERE email = :email";

    public static final String INSERT_USER_ACCOUNT_VERIFICATION_QUERY = "INSERT INTO UserAccountVerifications (user_id, url) VALUES (:userId, :url)";
}
