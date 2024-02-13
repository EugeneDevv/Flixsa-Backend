package com.flixsa.api.services;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by Eugene Devv on 13 Feb, 2024
 */
public interface UserService {

    UserDetailsService userDetailsService();
}
