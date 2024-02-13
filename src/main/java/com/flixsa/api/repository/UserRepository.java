package com.flixsa.api.repository;

import com.flixsa.api.entities.Role;
import com.flixsa.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Eugene Devv on 12 Feb, 2024
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    User findByRole(Role role);
}
