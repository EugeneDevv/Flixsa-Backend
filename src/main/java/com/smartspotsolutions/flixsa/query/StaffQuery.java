package com.smartspotsolutions.flixsa.query;

/**
 * Created by Eugene Devv on 20 Dec, 2023
 */
public class StaffQuery {
    public static final String COUNT_STAFF_EMAIL_QUERY = "SELECT COUNT(*) FROM Staff WHERE email = :email";
    public static final String INSERT_STAFF_QUERY = "INSERT INTO Staff (first_name, last_name, email, password) VALUES (:firstName, :lastName, :email, :password)";

    public static final String INSERT_STAFF_ACCOUNT_VERIFICATION_QUERY = "INSERT INTO StaffAccountVerifications (staff_id, url) VALUES (:staffId, :url)";
}
