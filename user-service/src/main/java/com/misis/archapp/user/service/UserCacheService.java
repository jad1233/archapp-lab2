package com.misis.archapp.user.service;

import com.misis.archapp.user.dto.UserDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserCacheService {

    private final UserService userService;

    public UserCacheService(UserService userService) {
        this.userService = userService;
    }

    @Cacheable(value = "users", key = "#id")
    public UserDTO getUserById(UUID id) {
        return userService.getUserFromDB(id);
    }
}