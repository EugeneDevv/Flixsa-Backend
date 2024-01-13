package com.smartspotsolutions.flixsa.service;

import com.smartspotsolutions.flixsa.domain.User;
import com.smartspotsolutions.flixsa.dto.UserDTO;

/**
 * Created by Eugene Devv on 12 Jan, 2024
 */
public interface UserService {
    UserDTO createUser(User user);
}
