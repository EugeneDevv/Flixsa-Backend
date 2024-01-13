package com.smartspotsolutions.flixsa.repository;

import com.smartspotsolutions.flixsa.domain.cinemas.Role;

import java.util.Collection;

/**
 * Created by Eugene Devv on 21 Dec, 2023
 */
public interface RoleRepository<T extends Role> {
//*    Basic CRUD operations */

    T create(T data);
    Collection<T> list(int page, int pageSize);
    T get(Long id);
    T update(T data);
    Boolean delete(Long id);

    void addRoleToStaff(Long userId, String roleName);

    Role getRoleByStaffId(Long staffId);

    Role getRoleByStaffEmail(String email);
    void updateStaffRole(Long staffId, Role role);
}
