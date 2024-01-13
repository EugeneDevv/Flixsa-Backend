package com.smartspotsolutions.flixsa.service.implementation;

import com.smartspotsolutions.flixsa.domain.User;
import com.smartspotsolutions.flixsa.dto.UserDTO;
import com.smartspotsolutions.flixsa.dtomapper.UserDTOMapper;
import com.smartspotsolutions.flixsa.repository.UserRepository;
import com.smartspotsolutions.flixsa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by Eugene Devv on 12 Jan, 2024
 */

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository<User> userRepository;

    @Override
    public UserDTO createUser(User user) {
        return UserDTOMapper.fromUser(userRepository.create(user));
    }
}



