package net.springboot.user_management.service;

import net.springboot.user_management.dto.UserDto;
import net.springboot.user_management.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto createUser(UserDto user);
    UserDto getUserById(Long userId);
    List<UserDto> getAllUsers();
    UserDto updateUser(UserDto user);
    Boolean deleteUser(Long userId);
}
