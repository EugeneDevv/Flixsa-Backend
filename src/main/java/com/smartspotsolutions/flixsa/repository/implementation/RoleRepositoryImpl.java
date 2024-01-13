package com.smartspotsolutions.flixsa.repository.implementation;

import com.smartspotsolutions.flixsa.domain.cinemas.Role;
import com.smartspotsolutions.flixsa.exception.ApiException;
import com.smartspotsolutions.flixsa.repository.RoleRepository;
import com.smartspotsolutions.flixsa.rowmapper.RoleRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import static com.smartspotsolutions.flixsa.enumeration.RoleType.ROLE_USER;
import static com.smartspotsolutions.flixsa.query.RoleQuery.*;

/**
 * Created by Eugene Devv on 03 Jan, 2024
 */

@Repository
@RequiredArgsConstructor
@Slf4j
public class RoleRepositoryImpl implements RoleRepository<Role> {
    private final NamedParameterJdbcTemplate jdbc;

    @Override
    public Role create(Role data) {
        return null;
    }

    @Override
    public Collection<Role> list(int page, int pageSize) {
        return null;
    }

    @Override
    public Role get(Long id) {
        return null;
    }

    @Override
    public Role update(Role data) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public void addRoleToStaff(Long staffId, String roleName) {

        log.info("Adding role {} to user id: {}", roleName, staffId);

        try {
            Role role = jdbc.queryForObject(SELECT_ROLE_BY_NAME_QUERY, Map.of("roleName", roleName), new RoleRowMapper());
            jdbc.update(INSERT_ROLE_TO_STAFF_QUERY, Map.of("staffId", staffId, "roleId", Objects.requireNonNull(role).getId()));
        } catch (EmptyResultDataAccessException exception) {
            throw new ApiException("No role found by name: "+ ROLE_USER.name());
        } catch (Exception exception) {
            throw new ApiException("An error occurred. Please try again.");
        }

    }

    @Override
    public Role getRoleByStaffId(Long staffId) {
        return null;
    }

    @Override
    public Role getRoleByStaffEmail(String email) {
        return null;
    }

    @Override
    public void updateStaffRole(Long staffId, Role role) {

    }
}
