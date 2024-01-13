package com.smartspotsolutions.flixsa.query;

/**
 * Created by Eugene Devv on 03 Jan, 2024
 */
public class RoleQuery {

    public static final String SELECT_ROLE_BY_NAME_QUERY = "SELECT * FROM Roles WHERE name = :roleName";
    public static final String INSERT_ROLE_TO_STAFF_QUERY = "INSERT INTO StaffRoles (staff_id, role_id) VALUES (:staffId, :roleId)";
}
