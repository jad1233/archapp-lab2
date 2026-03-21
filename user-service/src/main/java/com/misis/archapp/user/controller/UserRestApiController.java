package com.misis.archapp.user.controller;

import com.misis.archapp.user.dto.UserCreateDTO;
import com.misis.archapp.user.dto.UserDTO;
import com.misis.archapp.user.dto.UserUpdateDTO;
import com.misis.archapp.user.service.UserService;
import com.misis.archapp.user.service.UserCacheService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserRestApiController {

    private final UserService userService;
    private final UserCacheService userCacheService;

    @Autowired
    public UserRestApiController(
        UserService userService,
        UserCacheService userCacheService
    ) {
        this.userService = userService;
        this.userCacheService = userCacheService;
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public UserDTO getUserById(@PathVariable("id") UUID id) {
        return userCacheService.getUserById(id);
    }

    @PostMapping
    public UserDTO createUser(@RequestBody @Valid UserCreateDTO userCreateDTO) {
        return userService.createUser(userCreateDTO);
    }

    @PatchMapping("{id}")
    public UserDTO updateUser(@PathVariable("id") UUID id, @RequestBody @Valid UserUpdateDTO userUpdateDTO) {
        return userService.updateUser(id, userUpdateDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") UUID id) {
        userService.deleteUser(id);
    }
}